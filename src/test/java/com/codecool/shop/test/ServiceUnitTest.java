package com.codecool.shop.test;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;
import com.codecool.shop.service.ProductService;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
public class ServiceUnitTest {

    @Test
    void getProductCategory_existingId_return_category() {
        ProductDao productDao = mock(ProductDao.class);
        ProductCategoryDao productCategoryDao = mock(ProductCategoryDao.class);
        SupplierDao supplierDao = mock(SupplierDao.class);
        ProductCategory productCategory = new ProductCategory("testName","testDepartment" ,"testDescription");
        ProductService productService = new ProductService(productDao, productCategoryDao, supplierDao);

        int existingCategoryId = 999;
        when(productCategoryDao.find(existingCategoryId)).thenReturn(productCategory);

        assertEquals(productCategory, productService.getProductCategory(existingCategoryId));
    }

    @Test
    void getProductCategory_non_existingId_return_null() {
        ProductDao productDao = mock(ProductDao.class);
        ProductCategoryDao productCategoryDao = mock(ProductCategoryDao.class);
        SupplierDao supplierDao = mock(SupplierDao.class);
        ProductService productService = new ProductService(productDao, productCategoryDao, supplierDao);

        int nonExistingCategoryId = 1000;
        when(productCategoryDao.find(nonExistingCategoryId)).thenReturn(null);

        assertNull(productService.getProductCategory(nonExistingCategoryId));
    }

    @Test
    void getProductSupplier_existing_supplier_id_return_supplier() {
        ProductDao productDao = mock(ProductDao.class);
        ProductCategoryDao productCategoryDao = mock(ProductCategoryDao.class);
        SupplierDao supplierDao = mock(SupplierDao.class);
        ProductService productService = new ProductService(productDao, productCategoryDao, supplierDao);

        Supplier supplier = new Supplier("testSupplier", "testDescription");

        int existingSupplierId = 999;
        when(supplierDao.find(existingSupplierId)).thenReturn(supplier);

        assertEquals(supplier, productService.getProductSupplier(existingSupplierId));
    }

    @Test
    void getProductSupplier_non_existing_supplier_return_null() {
        ProductDao productDao = mock(ProductDao.class);
        ProductCategoryDao productCategoryDao = mock(ProductCategoryDao.class);
        SupplierDao supplierDao = mock(SupplierDao.class);
        ProductService productService = new ProductService(productDao, productCategoryDao, supplierDao);

        int nonExistingSupplierId = 1000;
        when(supplierDao.find(nonExistingSupplierId)).thenReturn(null);

        assertNull(productService.getProductSupplier(nonExistingSupplierId));
    }

    @Test
    void getProductsForCategory_existing_cat_id_return_products() {
        ProductDao productDao = mock(ProductDao.class);
        ProductCategoryDao productCategoryDao = mock(ProductCategoryDao.class);
        SupplierDao supplierDao = mock(SupplierDao.class);
        ProductService productService = new ProductService(productDao, productCategoryDao, supplierDao);

        ProductCategory productCategory = new ProductCategory("testName", "testDepartment", "testDescription");
        Supplier supplier = new Supplier("testSupplierName", "testSupplierDescription");
        Product testProduct = new Product("testProductName", new BigDecimal(999), "USD", "testDescription", productCategory, supplier);

        List<Product> testProductList = new ArrayList<>();
        testProductList.add(testProduct);

        int existingCatId = 999;
        when(productCategoryDao.find(existingCatId)).thenReturn(productCategory);
        when(productDao.getBy(productCategory)).thenReturn(testProductList);

        assertEquals(testProductList, productService.getProductsForCategory(existingCatId));
    }

    @Test
    void getProductsForCategory_non_existing_cat_id_return_empty_list() {
        ProductDao productDao = mock(ProductDao.class);
        ProductCategoryDao productCategoryDao = mock(ProductCategoryDao.class);
        SupplierDao supplierDao = mock(SupplierDao.class);
        ProductService productService = new ProductService(productDao, productCategoryDao, supplierDao);

        int nonExistingCatId = 1000;
        when(productCategoryDao.find(nonExistingCatId)).thenReturn(null);

        List<Product> emptyList = new ArrayList<>();

        assertEquals(emptyList, productService.getProductsForCategory(nonExistingCatId));
    }

    @Test
    void getProductsForSupplier_existing_supplier_id_return_products() {
        ProductDao productDao = mock(ProductDao.class);
        ProductCategoryDao productCategoryDao = mock(ProductCategoryDao.class);
        SupplierDao supplierDao = mock(SupplierDao.class);
        ProductService productService = new ProductService(productDao, productCategoryDao, supplierDao);

        ProductCategory productCategory = new ProductCategory("testName", "testDepartment", "testDescription");
        Supplier supplier = new Supplier("testName", "testDescription");
        Product testProduct = new Product("testProductName", new BigDecimal(999), "USD", "testDescription", productCategory, supplier);

        List<Product> testProductList = new ArrayList<>();
        testProductList.add(testProduct);

        int existingSupplierId = 999;
        when(supplierDao.find(existingSupplierId)).thenReturn(supplier);
        when(productDao.getBy(supplier)).thenReturn(testProductList);

        assertEquals(testProductList, productService.getProductsForSupplier(existingSupplierId));
    }
    @Test
    void getProductsForSupplier_non_existing_supplier_id_return_empty_list() {
        ProductDao productDao = mock(ProductDao.class);
        ProductCategoryDao productCategoryDao = mock(ProductCategoryDao.class);
        SupplierDao supplierDao = mock(SupplierDao.class);
        ProductService productService = new ProductService(productDao, productCategoryDao, supplierDao);

        int nonExistingSupplierId = 1000;
        List<Product> emptyList = new ArrayList<>();

        when(supplierDao.find(nonExistingSupplierId)).thenReturn(null);

        assertEquals(emptyList, productService.getProductsForSupplier(nonExistingSupplierId));
    }

    @Test
    void findCategoryIdByName_existing_id_return_category_id() {
        ProductDao productDao = mock(ProductDao.class);
        ProductCategoryDao productCategoryDao = mock(ProductCategoryDao.class);
        SupplierDao supplierDao = mock(SupplierDao.class);
        ProductService productService = new ProductService(productDao, productCategoryDao, supplierDao);
        ProductCategory testProductCategory = mock(ProductCategory.class);

        String existingTestName = "testName";
        int testId = 999;
        when(productCategoryDao.findByName(existingTestName)).thenReturn(testProductCategory);
        when(testProductCategory.getId()).thenReturn(testId);

        assertEquals(testId, productService.findCategoryIdByName(existingTestName));
    }
}
