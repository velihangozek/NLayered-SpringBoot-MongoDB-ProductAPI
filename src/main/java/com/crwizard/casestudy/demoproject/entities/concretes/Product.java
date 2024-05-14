package com.crwizard.casestudy.demoproject.entities.concretes;


import jakarta.persistence.Id;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "products")
@Data
public class Product { //
    @Id
    private String sku;

    private String n;
    private String u;
    private String i;
    private int p;
    private int sp;
    private String s;
    private int d;
    private String t;
    private int sd;
    private int q;
    private String c;
    private String b;
    private String cc;


    public Product(String sku, String n, String u, String i, int p, int sp, String s, int d, String t, int sd, int q, String c, String b, String cc) {
        this.sku = sku;
        this.n = n;
        this.u = u;
        this.i = i;
        this.p = p;
        this.sp = sp;
        this.s = s;
        this.d = d;
        this.t = t;
        this.sd = sd;
        this.q = q;
        this.c = c;
        this.b = b;
        this.cc = cc;
    }
}