package com.javarush.task.task38.task3804;

public class ExceptionFactory {
    static Throwable createException(Enum exceptionEnum) {

        if (exceptionEnum == null) return new IllegalArgumentException();

        String enumValue = exceptionEnum.name();
        String message = enumValue.substring(0, 1)
                + enumValue.substring(1)
                .toLowerCase()
                .replace('_', ' ');

        switch (exceptionEnum.getClass().getSimpleName()) {
            case "ApplicationExceptionMessage":
                return new Exception(message);
            case "DatabaseExceptionMessage":
                return new RuntimeException(message);
            case "UserExceptionMessage":
                return new Error(message);
            default:
                return new IllegalArgumentException();
        }
    }
}

/**
 * public class ABCD_Factory {
 * static Throwable getException(Enum enumm) {
 * if (enumm == null) return new IllegalArgumentException();
 * String message = enumm.name().toLowerCase().replaceAll("[_]", " ");
 * String first = message.substring(0, 1).toUpperCase();
 * message = first + message.substring(1);
 * <p>
 * switch (enumm.getClass().getSimpleName()) {
 * case "ApplicationExceptionMessage":
 * return new Exception(message);
 * case "DatabaseExceptionMessage":
 * return new RuntimeException(message);
 * case "UserExceptionMessage":
 * return new Error(message);
 * default:
 * return new IllegalArgumentException();
 * }
 */