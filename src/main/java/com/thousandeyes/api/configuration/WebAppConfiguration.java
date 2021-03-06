package com.thousandeyes.api.configuration;

import com.thousandeyes.api.cache.MemcacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Properties;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.thousandeyes.api")
public class WebAppConfiguration {
    private String username;
    private String password;
    private String url;
    private String driverClassName;
    private static final String USERNAME_PROP_NAME = "username";
    private static final String PASSWORD_PROP_NAME = "password";
    private static final String URL_PROP_NAME = "url";
    private static final String DRIVER_PROP_NAME = "driverClassName";
    private static final String MEM_ADDRESS = "address";
    private static final String MEM_PORT = "port";
    private static final String MEM_TTL = "ttl";
    private String memAddress;
    private int memPort;
    private int memTtl;

    @Bean
    public MemcacheManager memcacheManager(){
        try{
            readMemcacheProp();
        } catch (IOException e){
            e.printStackTrace();
            return null;
        }
        MemcacheManager manager = new MemcacheManager();
        InetAddress address = null;
        try {
            address = InetAddress.getByName(memAddress);
        } catch (UnknownHostException e) {
            return null;
        }
        manager.setAddress(address);
        manager.setPort(memPort);
        manager.setTimeToLive(memTtl);
        return manager;
    }

    private void readMemcacheProp() throws IOException {
        Properties properties = new Properties();
        InputStream jdbcFileInput = this.getClass().getResourceAsStream("/memcache.properties");
        properties.load(jdbcFileInput);
        memAddress = properties.getProperty(MEM_ADDRESS);
        memPort = Integer.valueOf(properties.getProperty(MEM_PORT));
        memTtl = Integer.valueOf(properties.getProperty(MEM_TTL));
    }

    @Bean
    public DataSource dataSource(){
        try {
            readProperties();
        } catch (IOException e){
            e.printStackTrace();
            return null;
        }
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(driverClassName);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        return dataSource;
    }

    private void readProperties() throws IOException {
        Properties properties = new Properties();
        InputStream jdbcFileInput = this.getClass().getResourceAsStream("/jdbc.properties");
        properties.load(jdbcFileInput);
        username = properties.getProperty(USERNAME_PROP_NAME);
        password = properties.getProperty(PASSWORD_PROP_NAME);
        url = properties.getProperty(URL_PROP_NAME);
        driverClassName = properties.getProperty(DRIVER_PROP_NAME);
    }

}