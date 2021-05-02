package ru.job4j.task;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZipMain {
    public void packFiles(List<Path> sources, File target) {
        try (ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(target))) {
            for (Path source : sources) {
                zos.putNextEntry(new ZipEntry(source.toString()));
                try (BufferedInputStream out = new BufferedInputStream(
                        new FileInputStream(String.valueOf(source)))) {
                    zos.write(out.readAllBytes());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) throws ParseException, IOException {
        ZipMain zipMain = new ZipMain();
        ArgsMap argsMap = ArgsMap.of(args);
        String path = argsMap.get("d");
        String predicate = argsMap.get("n");
        String type = argsMap.get("t");
        File target = new File(argsMap.get("o"));
        List<Path> list;
        switch (type) {
            case "mask":

                break;
            case "name":
                list = SearchFile.search(
                        Paths.get(path), p -> p.startsWith(predicate));
                zipMain.packFiles(list, target);
                break;
            case "regex":
                list = SearchFile.search(
                        Paths.get(path), p -> p.toString().matches(predicate));
                zipMain.packFiles(list, target);
                break;
        }
    }
}
