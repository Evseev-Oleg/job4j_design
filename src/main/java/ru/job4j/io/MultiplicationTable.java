package ru.job4j.io;

import java.io.FileOutputStream;

public class MultiplicationTable {
    public static void main(String[] args) {
        try (FileOutputStream out = new FileOutputStream("result.txt")) {
            for (int i = 1; i < 10; i++) {
                for (int j = 1; j < 10; j++) {
                    String res = i * j + " ";
                    out.write(res.getBytes());
                }
                out.write("\n".getBytes());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
