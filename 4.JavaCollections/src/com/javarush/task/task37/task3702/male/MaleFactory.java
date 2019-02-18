package com.javarush.task.task37.task3702.male;

import com.javarush.task.task37.task3702.AbstractFactory;
import com.javarush.task.task37.task3702.Human;

public class MaleFactory implements AbstractFactory {
    public Human getPerson(int i) {
        if (i <= KidBoy.MAX_AGE) {
            return new KidBoy();
        }
        else if (i <= TeenBoy.MAX_AGE) {
            return new TeenBoy();
        }
        else return new Man();
    }
}
