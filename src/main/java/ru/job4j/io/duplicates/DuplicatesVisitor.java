package ru.job4j.io.duplicates;

import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {
    private Set<Path> set = new HashSet<>();
    private List<Path> listPath = new ArrayList<>();

    public List<Path> getListPath() {
        return listPath;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
        if (!set.add(file)) {
            listPath.add(file);
        }
        return FileVisitResult.CONTINUE;
    }
}
