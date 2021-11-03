package com.codecool.shop.test;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.service.ProductService;
import org.junit.jupiter.api.Test;
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

        int categoryId = 999;
        when(productCategoryDao.find(categoryId)).thenReturn(productCategory);

        assertEquals(productCategory, productService.getProductCategory(categoryId));
    }


}
