package com.codecool.shop.config;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.dao.implementation.SupplierDaoMem;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;
import com.codecool.shop.service.ProductService;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.math.BigDecimal;

@WebListener
public class Initializer implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {


        //ProductDao productDataStore = ProductDaoMem.getInstance();
        //ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();
        //SupplierDao supplierDataStore = SupplierDaoMem.getInstance();
        ProductService productService = new ProductService();
        productService.clearDatabase();


        //setting up a new supplier
        Supplier student = new Supplier("student", "Random stuffs from students");
        productService.addSupplier(student);
        Supplier mentor = new Supplier("mentor", "Random stuffs from mentors");
        productService.addSupplier(mentor);
        Supplier codecool = new Supplier("codecool", "Random stuff from CodeCool");
        productService.addSupplier(codecool);

        //setting up a new product category
        ProductCategory electronics = new ProductCategory("electronics", "Hardware", "A tablet computer, commonly shortened to tablet, is a thin, flat mobile computer with a touchscreen display.");
        productService.addProductCategory(electronics);
        ProductCategory home = new ProductCategory("home", "Hardware", "A tablet computer, commonly shortened to tablet, is a thin, flat mobile computer with a touchscreen display.");
        productService.addProductCategory(home);
        ProductCategory fashion = new ProductCategory("fashion", "Hardware", "A tablet computer, commonly shortened to tablet, is a thin, flat mobile computer with a touchscreen display.");
        productService.addProductCategory(fashion);
        ProductCategory sport = new ProductCategory("sport", "Hardware", "A tablet computer, commonly shortened to tablet, is a thin, flat mobile computer with a touchscreen display.");
        productService.addProductCategory(sport);

        //setting up products and printing it
        productService.addProduct(new Product("Pilgrim Destination", new BigDecimal("49"), "USD", "Visited by people time to time", home, codecool));
        productService.addProduct(new Product("Peti's Backpack in warm autumn color", new BigDecimal("479"), "USD", "Be like Peti,  with this mustard yellow backpack.", fashion, student));
        productService.addProduct(new Product("Krici's Laptop", new BigDecimal("89"), "USD", "Krici's laptop in not such a bad condition", electronics, student));
        productService.addProduct(new Product("Motivation Poster", new BigDecimal("10"), "USD", "Keep distance keep smiling", home, codecool));
        productService.addProduct(new Product("Dinner-set", new BigDecimal("130"), "USD", "Welcome your friend with this eclectic set", home, codecool));
        productService.addProduct(new Product("ArtDeco Sculpture-set 3pcs", new BigDecimal("150"), "USD", "Pure art", home, codecool));
        productService.addProduct(new Product("Road Racer", new BigDecimal("1000"), "USD", "Healthy is the new sexy", sport, codecool));
        productService.addProduct(new Product("Mug Top with Rubber Ring", new BigDecimal("12"), "USD", "This is it", home, codecool));
        productService.addProduct(new Product("Black Sabbath Poster for Collectors", new BigDecimal("2000"), "USD", "Metal for life", home, codecool));
        productService.addProduct(new Product("The Fountain", new BigDecimal("20000"), "USD", "Fight for it", electronics, mentor));
        productService.addProduct(new Product("Two Wheels Vehicle", new BigDecimal("30"), "USD", "We dont't know who owns this", sport, mentor));
        productService.addProduct(new Product("Money Pit", new BigDecimal("10"), "USD", "I had money once", electronics, codecool));
        productService.addProduct(new Product("Always Taken Armchair", new BigDecimal("150"), "USD", "With Bonus sitting mentors", fashion, mentor));
        productService.addProduct(new Product("Game Machine", new BigDecimal("140"), "USD", "For game or just for pushing the Buttons", sport, student));
    }
}
