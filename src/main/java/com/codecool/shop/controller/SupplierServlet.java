package com.codecool.shop.controller;

import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.dao.implementation.SupplierDaoMem;
import com.codecool.shop.model.Product;
import com.codecool.shop.service.ProductService;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

@WebServlet(urlPatterns = {"/supplier"})
public class SupplierServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String param = req.getParameter("param");

        ProductService productService = new ProductService();

        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());

        int supplierId = productService.findSupplierIdByName(param);

        context.setVariable("categoryNameCapitalised", productService.getWordCapitalised(param));
        context.setVariable("products", productService.getProductsForSupplier(supplierId));
        context.setVariable("totalCartCount", getCartItemCount(req));

            engine.process("product/category.html", context, resp.getWriter());
        }
    }

    protected int getCartItemCount(HttpServletRequest req){
        Map<Product, Integer> basket;

        HttpSession session = req.getSession();
        if (session.getAttribute("basket")!=null){
            int itemCount = 0;
            basket = (Map<Product, Integer>) session.getAttribute("basket");
            Object[] keys = basket.keySet().toArray();
            for (Object product : keys) {
                itemCount += basket.get((Product)product);
            }
            return itemCount;
        }
        return 0;
    }
}
