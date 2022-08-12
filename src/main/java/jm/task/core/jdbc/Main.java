package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;

public class Main {
    public static void main(String[] args) {
        Util util = new Util();
        User user1 = new User("Alex", "Fox", (byte) 33);

        String query =  "CREATE SCHEMA `zhtable`;";
        String query2 =  "CREATE TABLE `new_schema2`.`new_table` (\n" +
                "  `id` INT NOT NULL,\n" +
                "  `name` VARCHAR(45) NOT NULL,\n" +
                "  `lastName` VARCHAR(45) NOT NULL,\n" +
                "  `age` INT NOT NULL,\n" +
                "  PRIMARY KEY (`id`),\n" +
                "  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE);";

        try {
            Statement statement = util.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


//        final String URL = "jdbc:mysql://localhost:3306/test1";
//        final String USER = "root";
//        final String PASSWORD = "Root123456^";
//        Connection connection;

//        Driver driver;
//        {
//            try {
//                driver = new com.mysql.cj.jdbc.Driver();
//                DriverManager.registerDriver(driver);
////                connection = DriverManager.getConnection(URL, USER, PASSWORD);
////                if (!connection.isClosed()) {
////                    System.out.println("Соединение установлено");
////                }
////                System.out.printf("Conne");
//            } catch (SQLException e) {
//                throw new RuntimeException(e);
//            }
//        }
//
//        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
//             Statement statement = connection.createStatement()) {
//
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }

    }
}
