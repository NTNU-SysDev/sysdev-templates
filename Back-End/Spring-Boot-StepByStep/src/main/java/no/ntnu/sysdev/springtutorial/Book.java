package no.ntnu.sysdev.springtutorial;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class Book {
    @NotNull
    private int id;
    @NotBlank
    private String author;
    @NotBlank
    private String title;

    public Book(int id, String author, String title) {
        this.id = id;
        this.author = author;
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
