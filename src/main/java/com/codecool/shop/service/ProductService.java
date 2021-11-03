package com.codecool.shop.service;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.implementation.*;
import com.codecool.shop.model.BaseModel;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;
import com.codecool.shop.config.DataSourceConfig;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import org.postgresql.ds.PGSimpleDataSource;


public class ProductService {
    private ProductDao productDao;
    private ProductCategoryDao productCategoryDao;
    private SupplierDao supplierDao;
    DataSourceConfig dataSourceConfig = DataSourceConfig.getInstance();

    public ProductService() {
        if (dataSourceConfig.isDataSourceSql()){
            DataSource datasource = run();
            this.productDao = new ProductJdbc(datasource);
            this.productCategoryDao = new ProductCategoryJdbc(datasource);
            this.supplierDao = new SupplierJdbc(datasource);
        }else{
            this.productDao = ProductDaoMem.getInstance();
            this.productCategoryDao = ProductCategoryDaoMem.getInstance();
            this.supplierDao = SupplierDaoMem.getInstance();
        }
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
        Optional<Supplier> supplier = Optional.ofNullable(supplierDao.findByName(name));
        return supplier.map(BaseModel::getId).orElse(-1);
    }

    public String getWordCapitalised(String word){
        return productCategoryDao.getWordCapitalised(word);
    }

    public List<String> getAllCapitalised(){
        return productCategoryDao.getAllCapitalised();
    }

    public List<ProductCategory> getAllProductCategories(){
        return productCategoryDao.getAll();
    }

    public List<Product> getAllProducts(){
        return productDao.getAll();
    }

    public Product getProductById(int id){
        return productDao.find(id);
    }

    public void addProduct(Product product){
        productDao.add(product);
    }

    public void addProductCategory(ProductCategory category){
        productCategoryDao.add(category);
    }

    public void addSupplier(Supplier supplier){
        supplierDao.add(supplier);
    }


    public DataSource run() {
        try {
            return connect();
        } catch (SQLException throwables) {
            System.err.println("Could not connect to the database.");
            return null;
        }
    }


    private DataSource connect() throws SQLException {
        PGSimpleDataSource dataSource = new PGSimpleDataSource();
        dataSource.setDatabaseName("codecoolshop");
        dataSource.setUser("andi");
        dataSource.setPassword("code");
        System.out.println("Trying to connect...");
        dataSource.getConnection().close();
        System.out.println("Connection OK");

        return dataSource;
    }


}
