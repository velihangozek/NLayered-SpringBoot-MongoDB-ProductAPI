package com.crwizard.casestudy.demoproject.business.abstracts;

import com.crwizard.casestudy.demoproject.entities.concretes.Product;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface GzipFileService {
    public void processJsonFileFromUrl(String url);

    public List<Product> extractJsonContentFromFileName(String fileName);

}