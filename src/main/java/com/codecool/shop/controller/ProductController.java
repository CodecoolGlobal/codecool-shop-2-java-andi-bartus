package com.codecool.shop.controller;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.config.TemplateEngineUtil;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;

@WebServlet(urlPatterns = {"/"})
public class ProductController extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProductDao productDataStore = ProductDaoMem.getInstance();
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();

        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());

        context.setVariable("categories", productCategoryDataStore.getAll());
        context.setVariable("categoryNamesCapitalised", productCategoryDataStore.getAllCapitalised());
        context.setVariable("productMatrix", getProductsOfCategories(productDataStore.getAll(), productCategoryDataStore.getAll()));
        context.setVariable("totalCartCount", getCartItemCount(req));

        engine.process("product/index.html", context, resp.getWriter());
    }

    protected int getCartItemCount(HttpServletRequest req){
        Map<Product, Integer> basket;

        HttpSession session = req.getSession();
        if (session.getAttribute("basket")!=null){

            int itemCount = 0;
            System.out.println(session.getAttribute("basket").toString());
            basket = (Map<Product, Integer>) session.getAttribute("basket");
            Object[] keys = basket.keySet().toArray();
            for (Object product : keys) {
                itemCount += basket.get((Product)product);
            }
            return itemCount;
        }
        return 0;
    }

    protected List<List<Product>> getProductsOfCategories(List<Product> products, List<ProductCategory> categories){
        List<List<Product>> productsOfCategories = new ArrayList<>();
        for (int i = 0; i < categories.size(); i++) {
            productsOfCategories.add(new ArrayList<Product>());
            for (Product product: products) {
                if (categories.get(i).getName().equals(product.getProductCategory().getName())){
                    productsOfCategories.get(i).add(product);
                }
            }
        }
        return productsOfCategories;
    }
}
