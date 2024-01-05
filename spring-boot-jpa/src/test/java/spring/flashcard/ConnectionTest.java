package spring.flashcard;

import org.junit.jupiter.api.Test;
import org.springframework.boot.SpringApplication;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

/**
 * The {@link ConnectionTest} class is a JUnit test class for testing database connection setup.
 * It ensures that the application can establish a connection to the database using the properties
 * defined in the 'application.properties' file.
 */
class ConnectionTest {

    private final Logger logger = MyApplication.getLogger(ConnectionTest.class);

    /**
     * Asserts the establishment of a SQL database connection.
     * This test method verifies that the application can successfully connect to the database
     * using the configuration provided in the 'application.properties' file.
     */
    @Test
    void assertSQLConnection() {
        // We start the app
        String[] args = new String[0];
        SpringApplication.run(MyApplication.class, args);

        URL resourceUrl = getClass().getClassLoader().getResource("application.properties");
        Properties properties = new Properties();

        try {
            assert resourceUrl != null;
            try (InputStream  input = resourceUrl.openStream()) {
                properties.load(input);

                String dbUrl = properties.getProperty("spring.datasource.url");
                String dbUsername = properties.getProperty("spring.datasource.username");
                String dbPassword = properties.getProperty("spring.datasource.password");

                Connection connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);

                assertNotNull(connection, "Failed to establish a connection");
                logger.info("Test passed: Database connection established successfully: " + connection.getMetaData().getDatabaseProductName());

                connection.close(); // Close the connection
            }
        } catch (IOException | SQLException e) {
            fail("Test failed: " + e.getMessage());
        }
    }
}
