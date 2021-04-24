package ru.job4j.serialization;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Arrays;

public class Book {
    private final boolean availability;
    private final int count;
    private final String title;
    private final Contact contact;
    private final String[] statuses;

    public Book(boolean availability, int count, String title, Contact contact, String... statuses) {
        this.availability = availability;
        this.count = count;
        this.title = title;
        this.contact = contact;
        this.statuses = statuses;
    }

    @Override
    public String toString() {
        return "Book{" +
                "availability=" + availability
                + ", count=" + count
                + ", title='" + title + '\''
                + ", contact=" + contact
                + ", statuses=" + Arrays.toString(statuses)
                + '}';
    }

    public static void main(String[] args) {
        final Book book = new Book(false, 30, "Title"
                , new Contact(123, "11-111"), "Worker");

        final Gson gson = new GsonBuilder().create();
        System.out.println(gson.toJson(book));

        final String bookJson =
                "{"
                        + "\"availability\":false,"
                        + "\"count\":350,"
                        + "\"contact\":"
                        + "{"
                        + "\"phone\":\"+7(924)111-111-11-11\""
                        + "},"
                        + "\"statuses\":"
                        + "[\"Busy\",\"Free\"]"
                        + "}";
        final Book bookMod = gson.fromJson(bookJson, Book.class);
        System.out.println(bookMod);
    }
}
