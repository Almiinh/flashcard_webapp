package spring.flashcard;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Logger;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ConnectionTest {

    private final Logger logger = MyApplication.getLogger(ConnectionTest.class);

    @Test
    void assertSQLConnection() throws URISyntaxException {
        URL resourceUrl = getClass().getClassLoader().getResource("application.properties");
        Properties properties = new Properties();

        try (InputStream  input = resourceUrl.openStream()) {
            properties.load(input);

            String dbUrl = properties.getProperty("spring.datasource.url");
            String dbUsername = properties.getProperty("spring.datasource.username");
            String dbPassword = properties.getProperty("spring.datasource.password");

            Connection connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);

            assertNotNull(connection, "Failed to establish a connection");
            logger.info("Test passed: Database connection established successfully: " + connection.getMetaData().getDatabaseProductName());

            connection.close(); // Close the connection
        } catch (IOException | SQLException e) {
            fail("Test failed: " + e.getMessage());
        }
    }
}
