package com.crwizard.casestudy.demoproject.business.concretes;


import com.crwizard.casestudy.demoproject.business.abstracts.GzipFileService;
import com.crwizard.casestudy.demoproject.business.abstracts.ProductService;
import com.crwizard.casestudy.demoproject.entities.concretes.Product;
import com.crwizard.casestudy.demoproject.repository.abstracts.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductManager implements ProductService {

    private final GzipFileService gzipFileService;

    private final ProductRepository productRepository;

    public ProductManager(ProductRepository productRepository, GzipFileService gzipFileService) { // DI
        this.productRepository = productRepository;
        this.gzipFileService = gzipFileService;
    }

    @Override
    public void saveProductsToMongoDB(String fileName) {
        List<Product> products = gzipFileService.extractJsonContentFromFileName(fileName);
        this.productRepository.saveAll(products);
    }
    @Override
    public Product getProduct(String sku) { // Method that call the ProductRerpository interface i.e Dal Layer method
        return this.productRepository.findBySku(sku);
    }

    @Override
    public void saveProducts(List<Product> products) {
        this.productRepository.saveAll(products);
    }
}