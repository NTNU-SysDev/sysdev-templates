package no.ntnu.sysdev.javafx_client;

import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.net.ConnectException;
import java.net.UnknownHostException;
import java.util.regex.Pattern;

public class GUIController {

    // Client
    private RESTClient client;

    // FX Elements
    public Button btnCreateUser;
    public Button btnDelete;
    public Button btnRefresh;

    public TextField txtName;
    public TextField txtEmail;
    public TextField txtPhone;
    public TextField txtAge;
    public TextField txtHost;
    public TextField txtPort;

    public TabPane tabPane;

    public Tab tabManageUser;
    public Tab tabAddUser;

    public TableView tblUserList;

    public TableColumn colName;
    public TableColumn colEmail;
    public TableColumn colPhone;
    public TableColumn colAge;

    public GridPane mainPane;

    public ImageView imgStatus;

    public Label txtStatus;

    /**
     * Runs automatically upon running the project.
     */
    public void initialize() {
        prepareElements();
        setListeners();

        client = new RESTClient();
    }

    /**
     * Changes the property of the elements.
     */
    private void prepareElements() {
        // Alternative text when hovering over the buttons
        btnRefresh.setTooltip(new Tooltip("Refresh users"));
        btnDelete.setTooltip(new Tooltip("Delete user(s)"));

        // Assigns the data types for each column on the table
        colName.setCellValueFactory(new PropertyValueFactory<User, String>("name"));
        colEmail.setCellValueFactory(new PropertyValueFactory<User, String>("email"));
        colPhone.setCellValueFactory(new PropertyValueFactory<User, String>("phone"));
        colAge.setCellValueFactory(new PropertyValueFactory<User, Integer>("age"));

        // Enables multi-selection on the GUI table
        tblUserList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        // Sets default tab to "Manage User" (instead of default "Add User")
        tabPane.getSelectionModel().select(tabManageUser);
    }

    /**
     * Sets the action to preform when an event is triggered.
     */
    private void setListeners() {
        // Create user from text fields when button is pressed
        btnCreateUser.setOnMouseClicked(evt -> {
            String name = txtName.getText();
            String email = txtEmail.getText();
            String phone = txtPhone.getText().replace(" ", ""); // Removes whitespaces
            int age = Integer.parseInt(txtAge.getText());

            createUser(name, email, phone, age);
        });

        btnDelete.setOnMouseClicked(evt -> deleteUser());
        btnRefresh.setOnMouseClicked(evt -> refreshUsers());

        // Restricts to numeric values and spaces
        txtPhone.textProperty().addListener((obs, oldText, newText) -> {
            if (newText.matches("(\\d|\\s)*")) {
                txtPhone.setText(newText);
            } else {
                txtPhone.setText(oldText);
            }
        });

        // Restricts to numeric values
        txtAge.textProperty().addListener((obs, oldText, newText) -> {
            if (newText.matches("(\\d)*")) {
                txtAge.setText(newText);
            } else {
                txtAge.setText(oldText);
            }
        });

        // Restricts to numeric values
        txtPort.textProperty().addListener((obs, oldText, newText) -> {
            if (newText.matches("(\\d)*")) {
                txtPort.setText(newText);
            } else {
                txtPort.setText(oldText);
            }
        });

        // Refresh users when changing tabs
        tabManageUser.setOnSelectionChanged(evt -> refreshUsers());
    }

    /**
     * Sets the host and port for the current request.
     */
    private void setHost() {
        String host = txtHost.getText();
        int port;

        try {
            port = Integer.parseInt(txtPort.getText());
        } catch (NumberFormatException e) {
            // Prevents another exception when passing illegal characters as parameter to the client
            port = 0;
        }

        client.setServer(host, port);
    }

    /**
     * Create a new user.
     *
     * @param name      user's name
     * @param email     user's email
     * @param phone     user's phone
     * @param age       user's age
     *
     * @see RESTClient#createUser(User)
     */
    private void createUser(String name, String email, String phone, int age) {
        try {
            setHost();
            client.createUser(new User(name, email, phone, age));
            setStatus(client.getHttpResponse());
            refreshUsers();

            // Resets the text fields
            txtName.setText("");
            txtEmail.setText("");
            txtPhone.setText("");
            txtAge.setText("");
        } catch (ConnectException | UnknownHostException e) {
            setStatus("Couldn't connect to the host");
        } catch (IOException e) {
            setStatus(client.getHttpResponse() + " (Couldn't create user)");
        }
    }

    /**
     * Updates the GUI table with updated users.
     *
     * @see RESTClient#getUsers()
     */
    private void refreshUsers() {
        setHost();
        try {
            tblUserList.setItems(client.getUsers());
            setStatus(client.getHttpResponse());
        } catch (ConnectException | UnknownHostException e) {
            setStatus("Couldn't connect to the host");
        } catch (IOException | ParseException e) {
            setStatus(client.getHttpResponse() + " (Couldn't refresh user list)");
        }
    }

    /**
     * Gathers all selected users from the table and deletes them
     *
     * @see RESTClient#deleteUser(String) 
     */
    private void deleteUser() {
        setHost();
        // Stores all the selected rows from the GUI table
        ObservableList<User> selectedUsers = tblUserList.getSelectionModel().getSelectedItems();

        // Ensures there are users selected before deletion
        if (selectedUsers.size() > 0) {
            // Loops though them and deletes them individually
            for (User user : selectedUsers) {
                try {
                    client.deleteUser(user.getEmail());
                    setStatus(client.getHttpResponse());
                } catch (ConnectException | UnknownHostException e) {
                    setStatus("Couldn't connect to the host");
                } catch (IOException e) {
                    setStatus(client.getHttpResponse() + " (Couldn't delete user)");
                }
            }
            refreshUsers();
        }
    }

    /**
     * Sets the text and image positioned at the bottom of the GUI.
     *
     * @param statusResponse    HTTP code and response
     */
    private void setStatus(String statusResponse) {
        String img;

        if (Pattern.compile("HTTP/1.1 [1-3]\\d{2}(.*)").matcher(statusResponse).matches()) {
            // Checks if string contains status code from 100 to 300
            img = "/img/green.png";
        } else if (Pattern.compile("HTTP/1.1 [45]\\d{2}(.*)").matcher(statusResponse).matches()) {
            // Checks if string contains status code from 400 to 500
            img = "/img/red.png";
        } else {
            img = "/img/warning.png";
        }

        // Sets the image and the text
        imgStatus.setImage(new Image(getClass().getResourceAsStream(img)));
        txtStatus.setText(statusResponse);

        System.out.println(statusResponse);
    }
}
