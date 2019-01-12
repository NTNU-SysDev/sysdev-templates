package no.ntnu.sysdev.SpringBoot_Demo.entity;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * This User class is annotated with @Entity,
 * indicating that it is a JPA entity.
 * Could think of it representing a user table
 * in a database.
 */
@Entity
@Table(name = "user")
public class User {

    /**
     * The fields below represent our attributes
     * in the user table
     */

    @NotNull
    @NotEmpty
    @Column(name = "name")
    private String name;

    @Id
    @NotNull
    @NotEmpty
    @Column(name = "email", unique = true)
    private String email;

    @NotNull
    @NotEmpty
    @Column(name = "phone", unique = true)
    private String phone;

    @NotNull
    @Column(name = "age")
    private int age;

    public User() {

    }

    public User(String name, String email, String phone, int age) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public int getAge() {
        return age;
    }
}
