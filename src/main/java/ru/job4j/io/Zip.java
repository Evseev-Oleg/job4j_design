package ru.job4j.io;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {
    public void packFiles(List<Path> sources, File target) {
        try (ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(target))) {
            for (Path source : sources) {
                zos.putNextEntry(new ZipEntry(source.toString()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void packSingleFile(File source, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(
                new BufferedOutputStream(new FileOutputStream(target)))) {
            zip.putNextEntry(new ZipEntry(source.getPath()));
            try (BufferedInputStream out = new BufferedInputStream(
                    new FileInputStream(source))) {
                zip.write(out.readAllBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        Zip zip = new Zip();
        ArgsName jvm = ArgsName.of(new String[]{
                "-d=C:\\projects\\job4j_design", "-e=xml", "-o=project.zip"});
        String path = jvm.get("d");
        String predicate = jvm.get("e");
        File target = new File(jvm.get("o"));
        List<Path> list = Search.search(
                Paths.get(path), p -> !p.endsWith(predicate));
        zip.packFiles(list, target);
        new Zip().packSingleFile(
                new File("./pom.xml"),
                new File("./pom.zip")
        );
    }
}
