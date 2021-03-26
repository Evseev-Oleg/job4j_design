package ru.job4j.io;

import org.junit.Test;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class AnalizyTest {
    @Test
    public void testUnavailable() throws IOException {
        Analizy analizy = new Analizy();
        analizy.unavailable("analizy.csv", "unavailable.csv");
        String file = "unavailable.csv";
        Path path = Paths.get(file);
        Scanner scanner = new Scanner(path);
        String res = scanner.next();
        assertThat(res, is("10:57:01;10:59:01"));
    }
}