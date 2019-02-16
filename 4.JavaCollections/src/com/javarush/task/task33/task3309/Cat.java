package com.javarush.task.task33.task3309;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Cat {
    public String name;
    public int age;

    public Cat(String name, int age) {
        this.name = name;
        this.age = age;
    }
    public Cat() {

    }
}
