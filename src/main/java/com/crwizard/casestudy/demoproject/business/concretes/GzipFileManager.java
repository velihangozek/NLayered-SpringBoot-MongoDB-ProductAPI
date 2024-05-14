package com.crwizard.casestudy.demoproject.business.concretes;

import com.crwizard.casestudy.demoproject.business.abstracts.GzipFileService;
import com.crwizard.casestudy.demoproject.entities.concretes.Product;
import com.crwizard.casestudy.demoproject.repository.abstracts.ProductRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.zip.GZIPInputStream;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.databind.JsonNode;


@Service
public class GzipFileManager implements GzipFileService {
    private final ObjectMapper objectMapper;
    private final RestTemplate restTemplate;

    public GzipFileManager(ObjectMapper objectMapper, RestTemplate restTemplate) { // Dependency Injection
        this.objectMapper = objectMapper;
        this.restTemplate = restTemplate;
    }

    public void processJsonFileFromUrl(String url) {
        try {
            // Get the gzip file content from the URL
            byte[] gzipContent = restTemplate.getForObject(url, byte[].class);
            if (gzipContent != null) {
                // Decompress the gzip content
                try (GZIPInputStream gzipInputStream = new GZIPInputStream(new ByteArrayInputStream(gzipContent));
                     InputStreamReader inputStreamReader = new InputStreamReader(gzipInputStream);
                     BufferedReader reader = new BufferedReader(inputStreamReader)) {

                    String line;
                    while ((line = reader.readLine()) != null) {
                        // Process each line (assume line contains filename with JSON products array)
                        extractJsonContentFromFileName(line);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    // Method to extract JSON content from the filename
    // Method to process products array
    public List<Product> extractJsonContentFromFileName(String fileName) {
        try {
            // Assuming JSON content is enclosed within square brackets []
            int startIndex = fileName.indexOf('[');
            int endIndex = fileName.lastIndexOf(']');
            if (startIndex != -1 && endIndex != -1) {
                String jsonObject = fileName.substring(startIndex, endIndex + 1);
                List<Product> products = objectMapper.readValue(jsonObject, new TypeReference<List<Product>>() {});
                return products;
            }
        } catch (IOException exception) {
            // Handle IOException
            exception.printStackTrace();
        }
        return null; // JSON content not found or invalid
    }
}