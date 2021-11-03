package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;

public class ProductCategoryJdbc implements ProductCategoryDao {
    private final DataSource dataSource;

    public ProductCategoryJdbc(DataSource datasource) {
        this.dataSource = datasource;
    }

    @Override
    public void add(ProductCategory category) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "INSERT INTO category (name, department, description) VALUES (?, ?, ?)";
            PreparedStatement st = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            st.setString(1, category.getName());
            st.setString(2, category.getDepartment());
            st.setString(3, category.getDescription());

            st.executeUpdate();
            ResultSet rs = st.getGeneratedKeys();
            rs.next(); // Read next returned value - in this case the first one. See ResultSet docs for more explaination
            category.setId(rs.getInt(1));

        } catch (SQLException throwables) {
            throw new RuntimeException("Error while adding new Category.", throwables);
        }

    }

    @Override
    public ProductCategory find(int id) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "SELECT name, department, description FROM category WHERE id = ?";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if (!rs.next()) { // first row was not found == no data was returned by the query
                return null;
            }

            ProductCategory productCategory = new ProductCategory(rs.getString(1), rs.getString(2), rs.getString(3));
            productCategory.setId(id); // we already knew author id, so we do not read it from database.
            return productCategory;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void remove(int id) {

    }

    @Override
    public ProductCategory findByName(String name) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "SELECT id, name, department, description FROM category WHERE name = ?";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, name);
            ResultSet rs = st.executeQuery();
            if (!rs.next()) {
                return null;
            }
            ProductCategory category = new ProductCategory(rs.getString(2), rs.getString(3), rs.getString(4));
            category.setId(rs.getInt(1));
            return category;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<ProductCategory> getAll() {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "SELECT id, name, department, description FROM category";
            ResultSet rs = conn.createStatement().executeQuery(sql);
            List<ProductCategory> result = new ArrayList<>();
            while (rs.next()) {
                ProductCategory category = new ProductCategory(rs.getString(2), rs.getString(3), rs.getString(4));
                category.setId(rs.getInt(1));
                result.add(category);
            }
            return result;
        } catch (SQLException e) {
            throw new RuntimeException("Error while reading all authors", e);
        }

    }

    @Override
    public List<String> getAllCapitalised() {
        List<ProductCategory> allCategories = getAll();
        ArrayList<String> modifiedNames = new ArrayList<>();
        String categoryName;
        for (ProductCategory category : allCategories) {
            categoryName = category.getName();
            categoryName = getWordCapitalised(categoryName);
            modifiedNames.add(categoryName);
        }
        return modifiedNames;

    }

    @Override
    public String getWordCapitalised(String word) {
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
}
