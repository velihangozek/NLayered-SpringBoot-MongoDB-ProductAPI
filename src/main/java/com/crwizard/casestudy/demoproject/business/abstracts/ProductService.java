package com.crwizard.casestudy.demoproject.business.abstracts;

import com.crwizard.casestudy.demoproject.entities.concretes.Product;

import java.util.List;

public interface ProductService {

    public void saveProductsToMongoDB(String fileName);
    public Product getProduct(String sku); // method signature
    public void saveProducts(List<Product> products);
}