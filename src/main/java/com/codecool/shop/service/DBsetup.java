package com.codecool.shop.service;

import org.postgresql.ds.PGSimpleDataSource;

import javax.sql.DataSource;
import java.sql.SQLException;

public class DBsetup {

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
