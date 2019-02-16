package com.javarush.task.task31.task3113;

import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

public class MyFileVisitor extends SimpleFileVisitor<Path> {
    private int dirCount = 0;
    private int filesCount = 0;
    private long size = 0;

    public void incrementDirCount() {
        dirCount++;
    }

    public void incrementFilesCount() {
        filesCount++;
    }

    public void upSize(long i) {
        size += i;
    }

    public int getDirCount() {
        return dirCount;
    }

    public int getFilesCount() {
        return filesCount;
    }

    public long getSize() {
        return size;
    }

    @Override
    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes atr) {
        incrementDirCount();

        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes atr) {
       // incrementFilesCount();
        incrementFilesCount();
        upSize(atr.size());

        return FileVisitResult.CONTINUE;
    }


}
