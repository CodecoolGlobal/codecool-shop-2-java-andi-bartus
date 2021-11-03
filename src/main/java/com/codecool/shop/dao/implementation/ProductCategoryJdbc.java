package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.model.ProductCategory;

import java.util.List;

public class ProductCategoryJdbc implements ProductCategoryDao {
    @Override
    public void add(ProductCategory category) {

    }

    @Override
    public ProductCategory find(int id) {
        return null;
    }

    @Override
    public void remove(int id) {

    }

    @Override
    public ProductCategory findByName(String name) {
        return null;
    }

    @Override
    public List<ProductCategory> getAll() {
        return null;
    }

    @Override
    public List<String> getAllCapitalised() {
        return null;
    }

    @Override
    public String getWordCapitalised(String word) {
        return null;
    }
}
