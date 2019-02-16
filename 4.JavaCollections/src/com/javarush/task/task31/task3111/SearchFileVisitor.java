package com.javarush.task.task31.task3111;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

public class SearchFileVisitor extends SimpleFileVisitor<Path> {
    private String partOfName;
    private String partOfContent;
    private int minSize = Integer.MIN_VALUE;
    private int maxSize = Integer.MAX_VALUE;
    private List<Path> foundFiles = new ArrayList<>();

    public void setPartOfName(String partOfName) {
        this.partOfName = partOfName;
    }

    public void setPartOfContent(String partOfContext) {
        this.partOfContent = partOfContext;
    }

    public void setMinSize(int minSize) {
        this.minSize = minSize;
    }

    public void setMaxSize(int maxSize) {
        this.maxSize = maxSize;
    }

    public List<Path> getFoundFiles() {
        return foundFiles;
    }


    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        byte[] content = Files.readAllBytes(file); // размер файла: content.length

        boolean inMax = true;
        if (content.length >= maxSize) {
            inMax = false;
        }

        boolean inMin = true;
        if (content.length <= minSize) {
            inMax = false;
        }

        boolean inContext = true;
        String s = new String(Files.readAllBytes(file));
        if (partOfContent != null && !s.contains(partOfContent)) {
            inContext = false;
        }

        boolean inPartOfName = true;
        if (partOfName != null && !file.getFileName().toString().contains(partOfName)) {
            inPartOfName = false;
        }

        if (inMax && inMin && inContext && inPartOfName) {
            foundFiles.add(file);
        }


        return FileVisitResult.CONTINUE; // super.visitFile(file, attrs);
    }
}
