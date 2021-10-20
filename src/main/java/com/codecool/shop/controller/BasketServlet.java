package com.codecool.shop.controller;

import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "BasketServlet", urlPatterns = {"/basket", "/addToBasket", "/removeFromBasket"})
public class BasketServlet extends HttpServlet {
    private Map<Product, Integer> basket = new HashMap<>();
    private final ProductDaoMem productDaoMem = ProductDaoMem.getInstance();


    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        String servletPath = request.getServletPath();
        int productId = Integer.parseInt(request.getParameter("id"));
        Product product = productDaoMem.find(productId);

        switch (servletPath) {
            case "/basket":
                //display basket arrayList field
                break;
            case "/addToBasket":
                basket.put(product, basket.getOrDefault(product, 0) + 1);
                session.setAttribute("basket", basket);
                break;
            case "/removeFromBasket":
                if (basket.get(product).equals(1)) {
                    basket.remove(product);
                }else {
                    basket.replace(product, basket.get(product), basket.get(product)-1);
                }
                session.setAttribute("basket", basket);
        }
    }

    public Map<Product, Integer> getBasket() {
        return this.basket;
    }



}
