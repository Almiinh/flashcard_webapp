package spring.flashcard;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.logging.Logger;

import org.json.JSONArray;
import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

public class IntegrationTest {

    Logger logger = MyApplication.getLogger(IntegrationTest.class);

    // Let's try further integration tests: a Status Code test and a JSON Payload test
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
        logger.info("Test 3 passed: JSON successfully found: " + payload);
        // Assert that the HTTP response is JSON

        connection.disconnect();
    }

    // We can also run all tests at once to check everything

}
