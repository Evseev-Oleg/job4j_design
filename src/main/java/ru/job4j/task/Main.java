package ru.job4j.task;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        ArgsMap argsMap = ArgsMap.of(args);
        SearchPredicate searchPredicate = new SearchPredicate(argsMap);
        try {
            searchPredicate.searchAndZip();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
