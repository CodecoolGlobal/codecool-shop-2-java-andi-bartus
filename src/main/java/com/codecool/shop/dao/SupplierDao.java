package com.codecool.shop.dao;

import com.codecool.shop.model.Supplier;

import java.util.List;
import java.util.Optional;

public interface SupplierDao {

    void add(Supplier supplier);
    Supplier find(int id);
    void remove(int id);
    void removeAllSupplier();
    Supplier findByName(String name);

    List<Supplier> getAll();
}
