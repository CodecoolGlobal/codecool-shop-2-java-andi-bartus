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

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.math.BigDecimal;

@WebListener
public class Initializer implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ProductDao productDataStore = ProductDaoMem.getInstance();
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();
        SupplierDao supplierDataStore = SupplierDaoMem.getInstance();

        //setting up a new supplier
        Supplier student = new Supplier("student", "Random stuffs from students");
        supplierDataStore.add(student);
        Supplier mentor = new Supplier("mentor", "Random stuffs from mentors");
        supplierDataStore.add(mentor);
        Supplier codecool = new Supplier("codecool", "Random stuff from CodeCool");
        supplierDataStore.add(codecool);

        //setting up a new product category
        ProductCategory electronics = new ProductCategory("electronics", "Hardware", "A tablet computer, commonly shortened to tablet, is a thin, flat mobile computer with a touchscreen display.");
        productCategoryDataStore.add(electronics);
        System.out.println("electronics" + electronics.getId());
        ProductCategory home = new ProductCategory("home", "Hardware", "A tablet computer, commonly shortened to tablet, is a thin, flat mobile computer with a touchscreen display.");
        productCategoryDataStore.add(home);
        ProductCategory fashion = new ProductCategory("fashion", "Hardware", "A tablet computer, commonly shortened to tablet, is a thin, flat mobile computer with a touchscreen display.");
        productCategoryDataStore.add(fashion);
        ProductCategory sport = new ProductCategory("sport", "Hardware", "A tablet computer, commonly shortened to tablet, is a thin, flat mobile computer with a touchscreen display.");
        productCategoryDataStore.add(sport);


        //setting up products and printing it
        productDataStore.add(new Product("Pilgrim Destination", new BigDecimal("49.9"), "USD", "Visited by people time to time", home, codecool));
        productDataStore.add(new Product("Peti's Backpack in warm autumn color", new BigDecimal("479"), "USD", "Be like Peti,  with this mustard yellow backpack.", fashion, student));
        productDataStore.add(new Product("Krici's Laptop", new BigDecimal("89"), "USD", "Krici's laptop in not such a bad condition", electronics, student));
        productDataStore.add(new Product("Motivation Poster", new BigDecimal("10"), "USD", "Keep distance keep smiling", home, codecool));
        productDataStore.add(new Product("Dinner-set", new BigDecimal("130"), "USD", "Welcome your friend with this eclectic set", home, codecool));
        productDataStore.add(new Product("ArtDeco Sculpture-set 3pcs", new BigDecimal("150"), "USD", "Pure art", home, codecool));
        productDataStore.add(new Product("Road Racer", new BigDecimal("1000"), "USD", "Healthy is the new sexy", sport, codecool));
        productDataStore.add(new Product("Mug Top with Rubber Ring", new BigDecimal("12"), "USD", "This is it", home, codecool));
        productDataStore.add(new Product("Black Sabbath Poster for Collectors", new BigDecimal("2000"), "USD", "Metal for life", home, codecool));
        productDataStore.add(new Product("The Fountain", new BigDecimal("20000"), "USD", "Fight for it", electronics, mentor));
        productDataStore.add(new Product("Two Wheels Vehicle", new BigDecimal("30"), "USD", "We dont't know who owns this", sport, mentor));
        productDataStore.add(new Product("Money Pit", new BigDecimal("10"), "USD", "I had money once", electronics, codecool));
        productDataStore.add(new Product("Always Taken Armchair", new BigDecimal("150"), "USD", "With Bonus sitting mentors", fashion, mentor));
        productDataStore.add(new Product("Game Machine", new BigDecimal("140"), "USD", "For game or just for pushing the Buttons", sport, student));
    }
}
