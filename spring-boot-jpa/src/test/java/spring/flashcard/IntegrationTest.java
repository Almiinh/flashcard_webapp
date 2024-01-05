package spring.flashcard;

import org.json.JSONArray;
import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * The {@link IntegrationTest} class contains integration tests for the application.
 * It verifies the status code of the web server and checks if the response contains valid JSON data.
 */
public class IntegrationTest {

    Logger logger = MyApplication.getLogger(IntegrationTest.class);

    /**
     * Test the HTTP status code of the web server.
     * This test method makes an HTTP GET request to the application's root URL and checks
     * if the response code is 200 (OK).
     *
     * @throws IOException if there is an issue with the HTTP connection
     */
    @Test
    public void testStatusCode() throws IOException {
        URL url = new URL("http://localhost:8081/");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        int responseCode = connection.getResponseCode();

        // Assert that the HTTP response code is 200 (OK)
        assertEquals(responseCode, HttpStatus.OK.value());
        logger.info("Test 2 passed: Website Server has started");

        connection.disconnect();
    }

    /**
     * Test if the HTTP response contains valid JSON data.
     * This test method makes an HTTP GET request to the '/api/cards' endpoint and checks if the
     * response contains valid JSON data.
     *
     * @throws IOException   if there is an issue with the HTTP connection
     * @throws JSONException if there is an issue parsing the JSON response
     */
    @Test
    public void testIfJSON() throws IOException, JSONException {
        URL url = new URL("http://localhost:8080/api/cards");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        StringBuilder content = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line);
                content.append(System.lineSeparator());
            }
        }
        JSONArray payload = new JSONArray(content.toString());
        assertNotNull(payload);
        logger.info("Test 3 passed: JSON payload successfully found: \n" + payload);
        // Assert that the HTTP response is JSON

        connection.disconnect();
    }

}
