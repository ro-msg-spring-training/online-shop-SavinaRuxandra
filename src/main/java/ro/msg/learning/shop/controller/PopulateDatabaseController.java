package ro.msg.learning.shop.controller;

import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.*;

@RestController
@Profile("test")
public class PopulateDatabaseController {

    private static final String DB_DRIVER = "org.h2.Driver";
    private static final String DB_CONNECTION = "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1";
    private static final String DB_USER = "";
    private static final String DB_PASSWORD = "";

    @RequestMapping(value = "/populateDatabase")
    public ResponseEntity<Object> populateDatabase() {
            Connection connection = getDBConnection();
            Statement stmt = null;
            try {
                connection.setAutoCommit(false);
                stmt = connection.createStatement();
                stmt.execute("INSERT INTO `supplier`(name) VALUES('ElectricShop'),('PullAndBear'),('Sephora')");
                stmt.execute("INSERT INTO `product_category` (name, description) VALUES('electronics', 'cheap'),('clothes', 'cute'),('Makeup', 'very nice')");
                stmt.execute("INSERT INTO `product` (name, description, price, weight, category, supplier, image_url) VALUES('laptop DELL', 'great', 100, 2, 1, 1, 'laptopUrl'),('shirt', 'cute', 20, 0.5, 2, 2, 'shirtUrl'),('lipstick', 'red', 25, 0.1, 3, 3, 'lipstickUrl')");
                stmt.execute("INSERT INTO `location` (name) VALUES('HERE'),('THERE')");
                stmt.execute("INSERT INTO `stock` (product, location, quantity) VALUES(1, 1, 11),(2, 2, 22),(1, 2, 12)");

                stmt.close();
                connection.commit();
            } catch (SQLException e) {
                System.out.println("Exception Message " + e.getLocalizedMessage());
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    connection.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
            return new ResponseEntity<>("The database has been populated", HttpStatus.OK);
        }

        @RequestMapping(value = "/dropDatabase")
        public ResponseEntity<Object> dropDatabase() {
            String connectionURL = "jdbc:h2:mem:test;drop=true";
            try {
                DriverManager.getConnection(connectionURL);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            return new ResponseEntity<>("The database has been deleted", HttpStatus.OK);
        }

    private static Connection getDBConnection() {
        Connection dbConnection = null;
        try {
            Class.forName(DB_DRIVER);
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        try {
            dbConnection = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);
            return dbConnection;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return dbConnection;
    }
}
