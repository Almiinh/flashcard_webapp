package com.bezkoder.spring.jpa.postgresql;

import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.logging.Logger;

class ConnectionTest {

    Logger logger = MyApplication.getLogger();

    // Let's test the connection to a PostgreSQL database
    @Test
    void assertPostgreSQLConnection() {
        try {
            // Get Connection
            Connection connection = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/testdb",
                    "postgres",
                    "postgres");

            // Assert that the connection is not null
            assert connection != null : "Failed to establish a connection";

            logger.info("Test 1 passed: PostgreSQL connection established successfully: "
                    + connection.getMetaData().getDatabaseProductName());

            // Close the connection
            connection.close();
        } catch (SQLException e) {
            logger.severe(Arrays.toString(e.getStackTrace()));
        }
    }

}
