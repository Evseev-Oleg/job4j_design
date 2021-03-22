package ru.job4j.collection;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Analize {
    public Info diff(List<User> previous, List<User> current) {
        Info info = new Info();
        Map<Integer, String> res = new HashMap<>();

        for (User user : current) {
            res.put(user.id, user.name);
        }

        for (User user : previous) {
            String check = res.remove(user.id);
            if (check == null) {
                info.deleted++;
            } else if (!check.equals(user.name)) {
                info.changed++;
            }
        }
        info.added = res.size();
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
