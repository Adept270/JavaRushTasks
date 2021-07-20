package com.javarush.task.task38.task3804;

import org.junit.Test;

import static org.junit.Assert.*;

public class ExceptionFactoryTest {

    @Test
    public void createException() {
        ExceptionFactory.createException(ApplicationExceptionMessage.UNHANDLED_EXCEPTION);
    }
}