package com.crwizard.casestudy.demoproject.api.controllers;

import com.crwizard.casestudy.demoproject.business.abstracts.GzipFileService;
import com.crwizard.casestudy.demoproject.business.abstracts.ProductService;
import com.crwizard.casestudy.demoproject.entities.concretes.Product;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductsController {

    private final ProductService productService;
    private final GzipFileService gzipFileService;

    public ProductsController(ProductService productService, GzipFileService gzipFileService) { //DI
        this.productService = productService;
        this.gzipFileService = gzipFileService;
    }

    @PostMapping("/processGzipFile")
    public ResponseEntity<String> processGzipFile() {
        try {
            // Call the service method to process the gzip file from URL
            gzipFileService.processJsonFileFromUrl("example.com/feeds.json");
            return ResponseEntity.ok().body("Gzip file processed successfully.");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to process the gzip file.");
        }

    }
    @PostMapping("/saveProducts") // Path annotation in Java EE
    public ResponseEntity<String> saveProducts(@RequestBody List<Product> products) {
        try {
            productService.saveProducts(products);
            return ResponseEntity.ok().body("Products saved successfully.");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to save products.");
        }
    }
    @GetMapping("/products/{sku}")
    public ResponseEntity<Product> getProductById(@PathVariable String sku) { // Related end-point method
        Product product = productService.getProduct(sku);
        if (product != null) {
            return ResponseEntity.ok(product);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}