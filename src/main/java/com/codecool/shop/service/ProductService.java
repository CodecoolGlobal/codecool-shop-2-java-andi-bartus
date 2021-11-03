package com.codecool.shop.service;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.model.BaseModel;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import org.postgresql.ds.PGSimpleDataSource;


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

    public void run() {
        try {
            connect();
        } catch (SQLException throwables) {
            System.err.println("Could not connect to the database.");
            return;
        }
    }


    private DataSource connect() throws SQLException {
        PGSimpleDataSource dataSource = new PGSimpleDataSource();
        dataSource.setDatabaseName("books2");
        dataSource.setUser("andi");
        dataSource.setPassword("code");
        System.out.println("Trying to connect...");
        dataSource.getConnection().close();
        System.out.println("Connection OK");

        return dataSource;
    }

}
