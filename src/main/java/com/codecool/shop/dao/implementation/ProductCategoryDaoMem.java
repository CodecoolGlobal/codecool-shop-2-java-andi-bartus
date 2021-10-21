package com.codecool.shop.dao.implementation;


import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class ProductCategoryDaoMem implements ProductCategoryDao {

    private List<ProductCategory> data = new ArrayList<>();
    private static ProductCategoryDaoMem instance = null;

    /* A private Constructor prevents any other class from instantiating.
     */
    private ProductCategoryDaoMem() {
    }

    public static ProductCategoryDaoMem getInstance() {
        if (instance == null) {
            instance = new ProductCategoryDaoMem();
        }
        return instance;
    }

    @Override
    public void add(ProductCategory category) {
        category.setId(data.size() + 1);
        data.add(category);
    }

    @Override
    public ProductCategory find(int id) {
        return data.stream().filter(t -> t.getId() == id).findFirst().orElse(null);
    }

    @Override
    public void remove(int id) {
        data.remove(find(id));
    }

    @Override
    public List<ProductCategory> getAll() {
        return data;
    }

    @Override
    public List<String> getAllCapitalised(){
        System.out.println(data.toString());
        ArrayList<String> modifiedNames = new ArrayList<>();
        for (ProductCategory category : data) {
            modifiedNames.add(getWordCapitalised(category.getName()));
        }
        System.out.println(data.toString());
        return modifiedNames;
    }

    @Override
    public String getWordCapitalised(String word){
        String modified = "";
        if (word.length() > 0){
            modified += word.charAt(0);
            modified = modified.toUpperCase();
            if (word.length() > 1){
                modified += word.substring(1);
            }
        }
        return modified;
    }

    @Override
    public ProductCategory findByName(String name) {
        ProductCategory category = data.stream()
                .filter(t -> t.getName().equals(name))
                .findFirst().orElse(null);
        return category;
    }
}
