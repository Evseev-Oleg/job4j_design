package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.HashSet;
import java.util.Set;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {
    private Set<Path> set = new HashSet<>();

    public Set<Path> getSet() {
        return set;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
        set.add(file);

//        FileProperty fileProperty = new FileProperty(file.toFile().length(), file.toFile().getName());
//        Files.lines(file).filter(a -> a.length() ==
//                fileProperty.getSize() && a.equals(fileProperty.getName()))
//                .forEach(System.out::println);
//            if (file.toFile().getName().equals(fileProperty.getName())
//                    && file.toFile().length() == fileProperty.getSize()) {
//                System.out.println(file.toAbsolutePath());
//        }

        return FileVisitResult.CONTINUE;
    }
}
