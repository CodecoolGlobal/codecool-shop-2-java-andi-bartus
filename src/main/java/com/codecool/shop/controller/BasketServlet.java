package com.codecool.shop.controller;

import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;
import com.google.gson.Gson;
import com.google.gson.JsonArray;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "BasketServlet", urlPatterns = {"/basket", "/addToBasket", "/removeFromBasket"})
public class BasketServlet extends HttpServlet {
    private Map<Product, Integer> basket = new HashMap<>();
    private final ProductDaoMem productDaoMem = ProductDaoMem.getInstance();


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        String servletPath = request.getServletPath();
        int productId = Integer.parseInt(request.getParameter("id"));
        Product product = productDaoMem.find(productId);

        switch (servletPath) {
            case "/basket":
                //display basket arrayList field
                break;
            case "/addToBasket":

                basket.put(product, basket.getOrDefault(product, 0)+1);
                session.setAttribute("basket", basket);
                System.out.println(basket);

                ArrayList<ArrayList<String>> datas = new ArrayList<>();

                for (Product key : basket.keySet()
                ) { ArrayList<String> data = new ArrayList<>();

                    data.add(String.valueOf(key.getId()));
                    data.add(key.getName());
                    data.add(key.getPrice());
                    data.add(String.valueOf(key.getDefaultPrice()));
                    data.add(String.valueOf(basket.get(key)));
                    datas.add(data);
                }

               // System.out.println(datas);

                JsonArray jsonArray1 = new Gson().toJsonTree(datas).getAsJsonArray();

                PrintWriter out = response.getWriter();
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                out.print(jsonArray1);
                out.flush();


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
