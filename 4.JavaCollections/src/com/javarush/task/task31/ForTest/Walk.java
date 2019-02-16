package com.javarush.task.task31.ForTest;


import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.MalformedInputException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class Walk {

    public static void main(String[] args) throws IOException {

        Path path = Paths.get("C:\\Users\\SBT-Ryabtsev-AV\\Downloads\\test");
        System.out.println(path.resolveSibling(Paths.get("Downloads\\test\\file")));


    }

}
