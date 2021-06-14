package ru.job4j.jdbc;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class TableEditor implements AutoCloseable {

    private Connection connection;
    private String url = null;
    private String login = null;
    private String password = null;
    private final Properties properties;


    public TableEditor(Properties properties) throws ClassNotFoundException, SQLException {
        this.properties = properties;
        initConnection();
    }

    private Connection initConnection() throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        try (FileInputStream fis = new FileInputStream("src/main/resources/app.properties")){
            properties.load(fis);
            url = properties.getProperty("db.url");
            login = properties.getProperty("db.login");
            password = properties.getProperty("db.password");
        } catch (IOException e) {
            e.printStackTrace();
        }
        connection = DriverManager.getConnection(url, login, password);
        return connection;
    }

    public void createTable(String tableName) {
        String sql = "CREATE TABLE " + tableName + "();";
        workTable(sql);
    }

    public void dropTable(String tableName) {
        String sql = "DROP TABLE " + tableName + ";";
        workTable(sql);
    }

    public void addColumn(String tableName, String columnName, String type) {
        String sql = "ALTER TABLE " + tableName
                + " ADD COLUMN " + columnName + " " + type + ";";
        workTable(sql);
    }

    public void dropColumn(String tableName, String columnName) {
        String sql = "ALTER TABLE " + tableName
                + " DROP COLUMN " + columnName + ";";
        workTable(sql);
    }

    public void renameColumn(String tableName, String columnName, String newColumnName) {
        String sql = "ALTER TABLE " + tableName
                + " RENAME COLUMN " + columnName + " TO " + newColumnName;
        workTable(sql);
    }

    public String getScheme(String tableName) throws SQLException {
        StringBuilder scheme = new StringBuilder();
        DatabaseMetaData metaData = connection.getMetaData();
        try (ResultSet columns = metaData.getColumns(null, null, tableName, null)) {
            scheme.append(String.format("%-15s %-15s%n", "column", "type"));
            while (columns.next()) {
                scheme.append(String.format("%-15s %-15s%n",
                        columns.getString("COLUMN_NAME"),
                        columns.getString("TYPE_NAME")));
            }
        }
        return scheme.toString();
    }

    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }

    public void workTable(String team) {
            try (Statement statement = connection.createStatement()) {
                statement.execute(team);
        } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Properties property = new Properties();
        try (FileInputStream fis = new FileInputStream("src/main/resources/app.properties")){
            property.load(fis);
        String url = property.getProperty("db.url");
        String login = property.getProperty("db.login");
        String password = property.getProperty("db.password");
        } catch (IOException e) {
            e.printStackTrace();
        }
        TableEditor tableEditor = new TableEditor(property);
        tableEditor.createTable("my_table");
        tableEditor.addColumn("my_table", "my_column", "VARCHAR(255)");
        tableEditor.renameColumn("my_table", "my_column", "my_first_column");
        tableEditor.dropColumn("my_table", "my_first_column");
        tableEditor.dropTable("my_table");
    }
}
