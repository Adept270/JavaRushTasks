package com.javarush.task.task33.task3313;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
//dd-MM-yyyy hh:mm:ss
public class Event {
    public String name;

    @JsonFormat(
            pattern = "dd-MM-yyyy hh:mm:ss",
            shape = JsonFormat.Shape.STRING
    )
    public Date eventDate;

    public Event(String name) {
        this.name = name;
        eventDate = new Date();
    }
}