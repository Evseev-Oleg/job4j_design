package ru.job4j.serialization;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JasonInPojo {
    public static void main(String[] args) {
        JSONObject jsonContact = new JSONObject(3412, "11-111");
        JSONObject jsonBook = new JSONObject("{"
                + "\"availability\":false,"
                + "\"count\":350,"
                + "\"contact\":"
                + "{"
                + "\"phone\":\"+7(924)111-111-11-11\""
                + "},"
                + "\"statuses\":"
                + "[\"Busy\",\"Free\"]"
                + "}");

        List<String> list = new ArrayList<>();
        list.add("Busy");
        list.add("Free");
        JSONArray jsonStatuses = new JSONArray(list);

        final Book book = new Book(false, 30, "Winter",
                new Contact(3412, "11-111"), "Free", "Busy");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("availability", book.isAvailability());
        jsonObject.put("count", book.getCount());
        jsonObject.put("title", book.getTitle());
        jsonObject.put("contact", jsonContact);
        jsonObject.put("statuses", jsonStatuses);

        System.out.println(jsonObject.toString());
        System.out.println(jsonBook.toString());
        System.out.println(new JSONObject(book).toString());
    }
}
