package ru.job4j.task;

import java.io.*;
import java.nio.file.Path;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZipFile {
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
}
