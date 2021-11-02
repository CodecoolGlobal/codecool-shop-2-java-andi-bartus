package com.codecool.shop.service;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.model.BaseModel;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import java.util.List;
import java.util.Optional;

public class ProductService {
    private ProductDao productDao;
    private ProductCategoryDao productCategoryDao;
    private SupplierDao supplierDao;

    public ProductService(ProductDao productDao, ProductCategoryDao productCategoryDao, SupplierDao supplierDao) {
        this.productDao = productDao;
        this.productCategoryDao = productCategoryDao;
        this.supplierDao = supplierDao;
    }

    public ProductCategory getProductCategory(int categoryId) {
        return productCategoryDao.find(categoryId);
    }

    public Supplier getProductSupplier(int supplierId) {
        return supplierDao.find(supplierId);
    }

    public List<Product> getProductsForCategory(int categoryId) {
        var category = productCategoryDao.find(categoryId);
        return productDao.getBy(category);
    }

    public List<Product> getProductsForSupplier(int suplierId) {
        var supplier = supplierDao.find(suplierId);
        return productDao.getBy(supplier);
    }

    public int findCategoryIdByName(String name) {
        ProductCategory productCategory = productCategoryDao.findByName(name);
        return productCategory.getId();
    }

    public int findSupplierIdByName(String name) {
        Optional<Supplier> supplier = supplierDao.findByName(name);
        return supplier.map(BaseModel::getId).orElse(-1);
        }
}
