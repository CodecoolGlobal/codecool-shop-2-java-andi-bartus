package com.codecool.shop.config;

public class DataSourceConfig{
    private final boolean sourceIsSql=true;
    private static DataSourceConfig instance = null;

    public static DataSourceConfig getInstance() {
        if (instance == null) {
            instance = new DataSourceConfig();
        }
        return instance;
    }

    public boolean isDataSourceSql(){
        return this.sourceIsSql;
    }

}
