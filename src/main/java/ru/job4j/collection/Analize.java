package ru.job4j.collection;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Analize {
    public Info diff(List<User> previous, List<User> current) {
        Info info = new Info();
        Map<Integer, String> prev = new HashMap<>();
        Map<Integer, String> curr = new HashMap<>();
        for (User user : previous) {
            prev.put(user.id, user.name);
        }
        for (User user : current) {
            curr.put(user.id, user.name);
        }
        for (Map.Entry<Integer, String> entryPrev : prev.entrySet()) {
            if (curr.containsKey(entryPrev.getKey())) {
                if (!entryPrev.getValue().equals(curr.get(entryPrev.getKey()))) {
                    info.changed++;
                }
            } else {
                info.deleted++;
            }
        }
        for (Map.Entry<Integer, String> entryCurr : curr.entrySet()) {
            if (!prev.containsKey(entryCurr.getKey())) {
                info.added++;
            }
        }
        return info;
    }

    public static class User {
        int id;
        String name;

        public User(int id, String name) {
            this.id = id;
            this.name = name;
        }
    }

    public static class Info {
        int added;
        int changed;
        int deleted;

    }
}
