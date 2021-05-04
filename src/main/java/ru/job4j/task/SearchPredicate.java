package ru.job4j.task;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class SearchPredicate {
    private final ArgsMap argsMap;
    private ZipFile zipFile;

    public SearchPredicate(ArgsMap argsMap) {
        this.argsMap = argsMap;
    }

    public List<Path> searchAndZip() throws IOException {
        String path = argsMap.get("d");
        String predicate = argsMap.get("n");
        String type = argsMap.get("t");
        File target = new File(argsMap.get("o"));
        if (path == null || predicate == null || type == null || target.toString() == null) {
            throw new IllegalArgumentException();
        }
        List<Path> list;
        switch (type) {
            case "mask" -> {
                StringBuilder result = null;
                for (int i = 0; i < predicate.length(); i++) {
                    if (predicate.charAt(i) == '*') {
                        result.append(".");
                    } else if (predicate.charAt(i) == '.') {
                        result.append("\\.");
                    } else {
                        result.append(predicate.charAt(i));
                    }
                }
                list = SearchFile.search(
                        Paths.get(path), p -> p.toString().matches(result.toString()));
                zipFile.packFiles(list, target);
            }
            case "name" -> {
                list = SearchFile.search(
                        Paths.get(path), p -> p.startsWith(predicate));
                zipFile.packFiles(list, target);
            }
            case "regex" -> {
                list = SearchFile.search(
                        Paths.get(path), p -> p.toString().matches(predicate));
                zipFile.packFiles(list, target);
            }
        }
        return null;
    }
}
