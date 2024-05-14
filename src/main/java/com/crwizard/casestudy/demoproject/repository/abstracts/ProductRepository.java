package com.crwizard.casestudy.demoproject.repository.abstracts;


import com.crwizard.casestudy.demoproject.entities.concretes.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends MongoRepository<Product,String> {
    Product findBySku(String sku); // JPQL Query

}