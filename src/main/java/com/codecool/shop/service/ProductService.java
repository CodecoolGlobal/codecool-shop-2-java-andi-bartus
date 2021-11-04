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
import java.util.List;
import java.util.Optional;



public class ProductService {
    private ProductDao productDao;
    private ProductCategoryDao productCategoryDao;
    private SupplierDao supplierDao;
    DataSourceConfig dataSourceConfig = DataSourceConfig.getInstance();

    public ProductService() {
        if (dataSourceConfig.isDataSourceSql()){
            DBsetup setup = new DBsetup();
            DataSource datasource = setup.run();
            this.productDao = new ProductJdbc(datasource);
            this.productCategoryDao = new ProductCategoryJdbc(datasource);
            this.supplierDao = new SupplierJdbc(datasource);
        }else{
            this.productDao = ProductDaoMem.getInstance();
            this.productCategoryDao = ProductCategoryDaoMem.getInstance();
            this.supplierDao = SupplierDaoMem.getInstance();
        }
    }

    //overloaded constructor for dependency injection because constructor has been rewritten, and my tests need dependencies...
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

    public List<Product> getProductsForSupplier(int supplierId) {
        var supplier = supplierDao.find(supplierId);
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


    public void clearDatabase(){
        productDao.removeAll();
        productCategoryDao.removeAll();
        supplierDao.removeAll();
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




}
