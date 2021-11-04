package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.model.Supplier;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.sql.DataSource;

public class SupplierJdbc implements SupplierDao {
    private final DataSource dataSource;

    public SupplierJdbc(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void add(Supplier suppliers) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "INSERT INTO supplier (name, description) VALUES (?, ?)";
            PreparedStatement st = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            st.setString(1, suppliers.getName());
            st.setString(2, suppliers.getDescription());
            st.executeUpdate();
            ResultSet rs = st.getGeneratedKeys();
            rs.next(); // Read next returned value - in this case the first one. See ResultSet docs for more explaination
            suppliers.setId(rs.getInt(1));

        } catch (SQLException throwables) {
            throw new RuntimeException("Error while adding new Supplier.", throwables);
        }

    }

    @Override
    public Supplier find(int id) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "SELECT name, description FROM supplier WHERE id = ?";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if (!rs.next()) { // first row was not found == no data was returned by the query
                return null;
            }

            Supplier supplier = new Supplier(rs.getString(1), rs.getString(2));
            supplier.setId(id);
            return supplier;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void remove(int id) {

    }

    @Override
    public void removeAllSupplier() {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "DELETE FROM supplier";
            PreparedStatement st = conn.prepareStatement(sql);
            st.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error while deleting products", e);

        }

    }

    @Override
    public Supplier findByName(String name) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "SELECT id, name, description FROM supplier WHERE name = ?";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, name);
            ResultSet rs = st.executeQuery();
            if (!rs.next()) {
                return null;
            }

            Supplier supplier = new Supplier(rs.getString(2), rs.getString(3));
            supplier.setId(rs.getInt(1));
            return supplier;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public List<Supplier> getAll() {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "SELECT id, name, description FROM supplier";
            ResultSet rs = conn.createStatement().executeQuery(sql);
            List<Supplier> result = new ArrayList<>();
            while (rs.next()) {
                Supplier supplier = new Supplier(rs.getString(2), rs.getString(3));
                supplier.setId(rs.getInt(1));
                result.add(supplier);
            }
            return result;
        } catch (SQLException e) {
            throw new RuntimeException("Error while reading all authors", e);
        }
    }
}
