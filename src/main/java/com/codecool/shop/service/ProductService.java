package com.codecool.shop.service;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.dao.implementation.SupplierDaoMem;
import com.codecool.shop.model.BaseModel;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;
import com.codecool.shop.config.DataSourceConfig;

import java.util.List;
import java.util.Optional;

public class ProductService {
    private ProductDao productDao;
    private ProductCategoryDao productCategoryDao;
    private SupplierDao supplierDao;
    DataSourceConfig dataSourceConfig = DataSourceConfig.getInstance();

    public ProductService() {
        if (dataSourceConfig.isDataSourceSql()){
            //sql needs implementation in this version.
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
        Optional<Supplier> supplier = supplierDao.findByName(name);
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
}
