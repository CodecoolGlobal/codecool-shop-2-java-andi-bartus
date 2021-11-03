package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.model.Supplier;

import java.util.List;
import java.util.Optional;

public class SupplierJdbc implements SupplierDao {
    @Override
    public void add(Supplier supplier) {

    }

    @Override
    public Supplier find(int id) {
        return null;
    }

    @Override
    public void remove(int id) {

    }

    @Override
    public Optional<Supplier> findByName(String name) {
        return Optional.empty();
    }

    @Override
    public List<Supplier> getAll() {
        return null;
    }
}
