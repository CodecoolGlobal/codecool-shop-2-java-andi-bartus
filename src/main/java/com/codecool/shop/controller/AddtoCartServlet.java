package com.codecool.shop.controller;

import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import com.google.gson.JsonArray;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;


@WebServlet(urlPatterns = {"/add_to_cart"})
public class AddtoCartServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Supplier mentor = new Supplier("mentor", "Computers");
        ProductCategory electronics = new ProductCategory("electronics", "Hardware", "A tablet computer, commonly shortened to tablet, is a thin, flat mobile computer with a touchscreen display.");
        Product testpr = new Product("Amazon Fire", new BigDecimal("49.9"), "USD", "Fantastic price. Large content ecosystem. Good parental controls. Helpful technical support.", electronics, mentor);
        Product testpr2 = new Product("Lenovo IdeaPad Miix 700", new BigDecimal("479"), "USD", "Keyboard cover is included. Fanless Core m5 processor. Full-size USB ports. Adjustable kickstand.", electronics, mentor);
        Product testpr3 = new Product("Amazon Fire HD 8", new BigDecimal("89"), "USD", "Amazon's latest Fire HD 8 tablet is a great value for media consumption.", electronics, mentor);
        Map<Product, Integer> test = new HashMap<Product, Integer>();
        test.put(testpr, 1);
        test.put(testpr2, 1);
        test.put(testpr3, 1);

        //System.out.println(test);
        ArrayList<ArrayList<String>> datas = new ArrayList<>();



            for (Product key : test.keySet()
            ) { ArrayList<String> data = new ArrayList<>();

                data.add(String.valueOf(key.getId()));
                data.add(key.getName());
                data.add(key.getPrice());
                data.add(String.valueOf(key.getDefaultPrice()));
                data.add(String.valueOf(test.get(key)));
                datas.add(data);
            }

        System.out.println(datas);

        JsonArray jsonArray1 = new Gson().toJsonTree(datas).getAsJsonArray();

        PrintWriter out = resp.getWriter();
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        out.print(jsonArray1);
        out.flush();


    }
}
