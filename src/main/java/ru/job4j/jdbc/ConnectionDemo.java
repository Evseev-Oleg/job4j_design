package ru.job4j.jdbc;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionDemo {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        FileInputStream fis;
        Properties property = new Properties();
        String url = null;
        String login = null;
        String password = null;
        try {
            fis = new FileInputStream("src/main/resources/app.properties");
            property.load(fis);
            url = property.getProperty("db.url");
            login = property.getProperty("db.login");
            password = property.getProperty("db.password");
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (Connection connection = DriverManager.getConnection(url, login, password)) {
            DatabaseMetaData metaData = connection.getMetaData();
            System.out.println(metaData.getUserName());
            System.out.println(metaData.getURL());
        }
    }
}
