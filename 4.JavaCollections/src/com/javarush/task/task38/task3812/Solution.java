package com.javarush.task.task38.task3812;

/* 
Обработка аннотаций
*/

import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.stream.Stream;

public class Solution {
    public static void main(String[] args) {
        printFullyQualifiedNames(Solution.class);
        printFullyQualifiedNames(SomeTest.class);

        printValues(Solution.class);
        printValues(SomeTest.class);
    }

    public static boolean printFullyQualifiedNames(Class c) {
        boolean hasAnnotation = c.isAnnotationPresent(PrepareMyTest.class);

        if (hasAnnotation) {
            PrepareMyTest annotation = (PrepareMyTest) c.getAnnotation(PrepareMyTest.class);
            Arrays.stream(annotation.fullyQualifiedNames()).forEach(System.out::println);
            return true;
        }
        return false;
    }

    public static boolean printValues(Class c) {
        boolean hasAnnotation = c.isAnnotationPresent(PrepareMyTest.class);

        if (hasAnnotation) {
            PrepareMyTest annotation = (PrepareMyTest) c.getAnnotation(PrepareMyTest.class);
            Arrays.stream(annotation.value()).forEach(aClass -> System.out.println(aClass.getSimpleName()));
            return true;
        }
        return false;

    }


}
