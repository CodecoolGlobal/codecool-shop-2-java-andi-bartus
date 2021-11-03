package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;
import javax.sql.DataSource;
import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class ProductJdbc implements ProductDao {
    private final DataSource dataSource;
    ProductCategoryDao categoryDao;
    SupplierDao supplierDao;

    public ProductJdbc(DataSource dataSource) {
        this.dataSource = dataSource;
        this.categoryDao = new ProductCategoryJdbc(dataSource);
        this.supplierDao = new SupplierJdbc(dataSource);
    }

    @Override
    public void add(Product product) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "INSERT INTO product (name, value, currency, description,category_id, supplier_id) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement st = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            st.setString(1, product.getName());
            st.setString(2, String.valueOf(product.getDefaultPrice()));
            st.setString(3, (String.valueOf(product.getDefaultCurrency())));
            st.setString(4, (product.getDescription()));
            st.setInt(5, (product.getProductCategory().getId()));
            st.setInt(6, (product.getSupplier().getId()));

            st.executeUpdate();
            ResultSet rs = st.getGeneratedKeys();
            rs.next();
            product.setId(rs.getInt(1));

        } catch (SQLException throwables) {
            throw new RuntimeException("Error while adding product.", throwables);
        }
    }



    @Override
    public Product find(int id) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "SELECT name, value, currency, description, category_id, supplier_id FROM product WHERE id = ?";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if (!rs.next()) { // first row was not found == no data was returned by the query
                return null;
            }

            ProductCategory category = categoryDao.find(rs.getInt(5));
            Supplier supplier = supplierDao.find(rs.getInt(6));

            Product product = new Product(rs.getString(1), new BigDecimal(rs.getString(2)), rs.getString(3), rs.getString(4), category, supplier);
            product.setId(id);
            return product;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void remove(int id) {

    }

    @Override
    public List<Product> getAll() {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "SELECT id, name, value, currency, description, category_id, supplier_id FROM product";
            ResultSet rs = conn.createStatement().executeQuery(sql);
            List<Product> result = new ArrayList<>();
            while (rs.next()) {
                ProductCategory category = categoryDao.find(rs.getInt(6));
                Supplier supplier = supplierDao.find(rs.getInt(7));

                Product product = new Product(rs.getString(2), new BigDecimal(rs.getString(3)), rs.getString(4), rs.getString(5), category, supplier);
                product.setId(rs.getInt(1));
                result.add(product);
            }
            return result;
        } catch (SQLException e) {
            throw new RuntimeException("Error while reading all authors", e);
        }
    }

    @Override
    public List<Product> getBy(Supplier supplier) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "SELECT id, name, value, currency, description, category_id, supplier_id FROM product WHERE supplier_id = ?";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, supplier.getId());
            ResultSet rs = conn.createStatement().executeQuery(sql);

            List<Product> result = new ArrayList<>();
            while (rs.next()) {
                ProductCategory category = categoryDao.find(rs.getInt(6));
                Supplier resSupplier = supplierDao.find(supplier.getId());

                Product product = new Product(rs.getString(2), new BigDecimal(rs.getString(3)), rs.getString(4), rs.getString(5), category, resSupplier);
                product.setId(rs.getInt(1));
                result.add(product);
            }
            return result;
        } catch (SQLException e) {
            throw new RuntimeException("Error while reading all authors", e);
        }

    }

    @Override
    public List<Product> getBy(ProductCategory productCategory) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "SELECT id, name, value, currency, description, category_id, supplier_id FROM product WHERE category_id = $1";
            PreparedStatement st = conn.prepareStatement(sql);
            System.out.println(productCategory.getId());
            st.setInt(1, productCategory.getId());

            ResultSet rs = conn.createStatement().executeQuery(sql);
            System.out.println("kdewdjjedmjenmjenfjenfcjenfc");
            List<Product> result = new ArrayList<>();
            while (rs.next()) {
                ProductCategory category = categoryDao.find(productCategory.getId());
                Supplier resSupplier = supplierDao.find(rs.getInt(7));

                Product product = new Product(rs.getString(2), new BigDecimal(rs.getString(3)), rs.getString(4), rs.getString(5), category, resSupplier);
                product.setId(rs.getInt(1));
                result.add(product);
            }
            return result;
        } catch (SQLException e) {
            throw new RuntimeException("Error while reading all authors", e);
        }
    }
}
