package com.javarush.task.task33.task3310.strategy;

import java.sql.*;

public class DataBaseStorageStrategy implements StorageStrategy {
    private Statement statement;
    private Connection connection;

    public DataBaseStorageStrategy() {
        try {
            Connection connection = DriverManager.getConnection("jdbc:h2:mem:");
            this.connection = connection;

            Statement statement = connection.createStatement();
            this.statement = statement;

            statement.execute("CREATE TABLE EXAMPLE (" +
                    "KEY VARCHAR(2000) PRIMARY KEY, VALUE VARCHAR(2000)" +
                    ")");
            System.out.println("Table has created.");
//            statement.execute("insert into example values( 'volume_1', 'volume_2 ')");
//            ResultSet rs = statement.executeQuery("select * from example");
//            while (rs.next()) {
//                System.out.printf("key: %s, value: %s", rs.getString(1), rs.getString(2));
//            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void close() {
        try {
            statement.close();
            connection.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public boolean containsKey(Long key) {
        try {
            ResultSet rs = statement.executeQuery("SELECT VALUE FROM EXAMPLE WHERE KEY = '" + key + "'");
            if (rs.next()) return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    @Override
    public boolean containsValue(String value) {
        try {
            ResultSet rs = statement.executeQuery("select * from example where value = '" + value + "'");
            if (rs.next()) return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    @Override
    public void put(Long key, String value) {
        if (containsKey(key)) {
            System.out.println("Table already has key: " + key);
        } else {
            try {
                statement.execute("insert into example values('" + key + "'," +
                        "'" + value + "')");
                System.out.printf("key %s and value %s has added into table", key, value);
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    @Override
    public Long getKey(String value) {
        try {
            ResultSet rs = statement.executeQuery("select * from example where value = '" + value + "'");
            if (rs.next()) return rs.getLong(1);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return null;
    }

    @Override
    public String getValue(Long key) {
        try {
            ResultSet rs = statement.executeQuery("select * from example where key = '" + key + "'");
            if (rs.next()) return rs.getString(2);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
