package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Analizy {

    public void unavailable(String source, String target) {
        List<String> list = new ArrayList<>();
        try (BufferedReader read = new BufferedReader(new FileReader(source))) {
            String stop = null;
            while (read.ready()) {
                String res = read.readLine();
                if (stop == null && (res.startsWith("400") || res.startsWith("500"))) {
                    list.add(res.substring(4));
                    stop = res;
                } else if (stop != null && (res.startsWith("200") || res.startsWith("300"))) {
                    list.add(res.substring(4));
                    stop = null;
                }
            }
            write(list, target);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void write(List<String> list, String target) {
        try (PrintWriter out = new PrintWriter(new FileOutputStream(target))) {
            for (int i = 0; i < list.size(); i += 2) {
                out.printf("%s;%s%n", list.get(i), list.get(i + 1));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try (PrintWriter out = new PrintWriter(new FileOutputStream("unavailable.csv"))) {
            out.println("15:01:30;15:02:32");
            out.println("15:10:30;23:12:32");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
