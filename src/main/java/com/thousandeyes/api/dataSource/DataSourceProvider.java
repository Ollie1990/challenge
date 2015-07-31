package com.thousandeyes.api.dataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by Roberto on 27/07/2015.
 */
public class DataSourceProvider {
    private static String username;
    private static String password;
    private static String url;
    private static String driverClassName;
    private static final String USERNAME_PROP_NAME = "username";
    private static final String PASSWORD_PROP_NAME = "password";
    private static final String URL_PROP_NAME = "url";
    private static final String DRIVER_PROP_NAME = "driverClassName";

    private static DriverManagerDataSource dataSource;


    public static DriverManagerDataSource getDataSource(){
        // read from properties
        try {
            readProperties();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }
        if (dataSource != null)
            return dataSource;
        dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(driverClassName);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        return dataSource;
    }

    private static void readProperties() throws IOException {
        Properties properties = new Properties();
        InputStream jdbcFileInput = DataSourceProvider.class.getResourceAsStream("/jdbc.properties");
//        FileInputStream jdbcFileInput = new FileInputStream("/WEB-INF/classes/jdbc.properties");
        properties.load(jdbcFileInput);
        username = properties.getProperty(USERNAME_PROP_NAME);
        password = properties.getProperty(PASSWORD_PROP_NAME);
        url = properties.getProperty(URL_PROP_NAME);
        driverClassName = properties.getProperty(DRIVER_PROP_NAME);
    }

//    public static void main(String args[]){
//        getDataSource();
//    }


    public static String getUsername() {
        return username;
    }

    public static void setUsername(String username) {
        DataSourceProvider.username = username;
    }

    public static String getPassword() {
        return password;
    }

    public static void setPassword(String password) {
        DataSourceProvider.password = password;
    }

    public static String getUrl() {
        return url;
    }

    public static void setUrl(String url) {
        DataSourceProvider.url = url;
    }

    public static String getDriverClassName() {
        return driverClassName;
    }

    public static void setDriverClassName(String driverClassName) {
        DataSourceProvider.driverClassName = driverClassName;
    }
}
