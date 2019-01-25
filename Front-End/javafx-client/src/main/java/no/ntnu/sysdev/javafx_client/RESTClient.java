package no.ntnu.sysdev.javafx_client;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Establishes communication with the API and interacts with it.
 */
public class RESTClient {

    private String host;
    private int port;
    private String httpResponse;

    /**
     * Creates an empty client.
     */
    public RESTClient() {}

    /**
     * Stores host and port of the API server for the current request.
     *
     * @param host          host name, excluding the protocol at the beginning of the url
     * @param port          port number
     */
    public void setServer(String host, int port) {
        this.host = host;
        this.port = port;
    }

    /**
     * Returns the last server-generated HTTP response from the latest request.
     *
     * @return  previous HTTP code and message
     */
    public String getHttpResponse() {
        return "HTTP/1.1 " + httpResponse;
    }

    /**
     * Main method that handles communication with the API.
     *
     * @param method        HTTP method (GET, POST, DELETE, etc...)
     * @param file          document file (that comes after the domain name)
     * @param contentType   HTTP header type (ex: application/json)
     * @param payload       HTTP parameters (ex: name=bob&age=2)
     * @return              response body from the server
     * @throws IOException  when something went wrong with the communication
     */
    private String request(String method, String file, String contentType, String payload) throws IOException {
        URL url = new URL("http", host, port, file);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();

        // Sets Content-Type header when defined
        if (contentType != null) {
            con.setRequestProperty("Content-Type", contentType);
        }

        // Sets HTTP method
        con.setRequestMethod(method);
        con.setDoInput(true);

        // Send parameters when defined
        if (payload != null) {
            con.setDoOutput(true);
            OutputStream outputStream = con.getOutputStream();
            outputStream.write(payload.getBytes());
            outputStream.close();
        }

        // Tries to connect to the server. If it fails, it will throw a ConnectionException instead of IOException
        try {
            con.connect();
        } catch (IOException ioe) {
            throw new ConnectException(ioe.getMessage());
        }

        // Stores the HTTP status
        httpResponse = con.getResponseCode() + " " + con.getResponseMessage();

        // Stores request body in a buffer
        BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
        StringBuilder response = new StringBuilder();

        String out;

        // Concatenates the input stream to a single string
        while ((out = reader.readLine()) != null) {
            response.append(out);
        }

        con.disconnect();

        return response.toString();
    }

    /**
     * Requests to receive all users.
     *
     * @return                  a list that can be used for the table in GUI
     * @throws ParseException   when users cannot be created from the JSON string
     * @throws IOException      if request to server fails
     *
     * @see #request(String, String, String, String)
     * @see User#createUsers(String)
     */
    public ObservableList<User> getUsers() throws IOException, ParseException {
        // Gets JSON containing all users after sending a request
        String jsonString = request("GET", "/users", "application/x-www-form-urlencoded", null);

        // Assign the variable to an empty JSON if request fails
        if (jsonString.equals("")) {
            jsonString = "[]";
        }

        // Convert JSON using the method inside User class and creates a list for GUI table
        return FXCollections.observableArrayList(User.createUsers(jsonString));
    }

    /**
     * Creates a user.
     *
     * @param user          user you want to add
     * @throws IOException  when request fails
     *
     * @see #request(String, String, String, String)
     */
    public void createUser(User user) throws IOException {
        JSONObject json = new JSONObject();

        // Extracts user data and put it in the JSON
        json.put("name", user.getName());
        json.put("email", user.getEmail());
        json.put("phone", user.getPhone());
        json.put("age", user.getAge());

        // Sends a creation request to the server with JSON in string format
        String response = request("POST", "/createUser", "application/json", json.toJSONString());
        System.out.println(response);
    }

    /**
     * Deletes a user.
     *
     * @param email         email of the user you want to delete
     * @throws IOException  when request fails
     *
     * @see #request(String, String, String, String)
     */
    public void deleteUser(String email) throws IOException {
        String response = request("DELETE", "/deleteUser", null, "email=" + email);
        System.out.println(response + " (" + email + ")");
    }

}
