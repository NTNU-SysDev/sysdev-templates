package no.ntnu.sysdev.javafx_client;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.ArrayList;

/**
 * Contains user data. Also able to create a list of users.
 */
public class User {
    private final SimpleStringProperty name;
    private final SimpleStringProperty email;
    private final SimpleStringProperty phone;
    private final SimpleIntegerProperty age;

    public User(String name, String email, String phone, int age) {
        this.name = new SimpleStringProperty(name);
        this.email = new SimpleStringProperty(email);
        this.phone = new SimpleStringProperty(phone);
        this.age = new SimpleIntegerProperty(age);
    }

    public String getName() {
        return name.get();
    }

    public String getEmail() {
        return email.get();
    }

    public String getPhone() {
        return phone.get();
    }

    public int getAge() {
        return age.get();
    }

    /**
     * Creates users from JSON string.
     *
     * @param jsonString        JSON string to parse
     * @return                  a list of created user
     * @throws ParseException   if the string isn't a valid JSON
     */
    public static ArrayList<User> createUsers(String jsonString) throws ParseException {
        JSONArray json = (JSONArray) new JSONParser().parse(jsonString);

        ArrayList<User> userList = new ArrayList<>();

        for (Object userObj : json) {
            JSONObject user = (JSONObject) userObj;

            String name = (String) user.get("name");
            String email = (String) user.get("email");
            String phone = (String) user.get("phone");
            int age = ((Long) user.get("age")).intValue();

            userList.add(new User(name, email, phone, age));
        }

        return userList;
    }
}
