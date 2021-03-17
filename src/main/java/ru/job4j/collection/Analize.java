package ru.job4j.collection;

import java.util.List;

public class Analize {
    public Info diff(List<User> previous, List<User> current) {
        Info info = new Info();
        boolean flag;
        for (User user : previous) {
            flag = false;
            for (User value : current) {
                if (user.id == value.id) {
                    flag = true;
                    if (!user.name.equals(value.name)) {
                        info.changed++;
                    }
                }
            }
            if (!flag) {
                info.deleted++;
            }
        }
        for (User user : current) {
            flag = false;
            for (User value : previous) {
                if (user.id == value.id) {
                    flag = true;
                }
            }
            if (!flag) {
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
