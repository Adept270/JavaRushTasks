package com.javarush.task.task36.task3602;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;
import java.util.Collections;
import java.util.List;

/*
Найти класс по описанию

1. Реализует интерфейс List;
2. Является приватным статическим классом внутри популярного утилитного класса;
3. Доступ по индексу запрещен - кидается исключение IndexOutOfBoundsException.
Используя рефлекшн (метод getDeclaredClasses), верни подходящий тип в методе getExpectedClass.
*/
public class Solution {
    public static void main(String[] args) {
        System.out.println(getExpectedClass());
    }

    public static Class getExpectedClass() {
        Class[] classes = Collections.class.getDeclaredClasses();
        for (Class c : classes) {
            if (List.class.isAssignableFrom(c)) {
                if (Modifier.isStatic(c.getModifiers())) {
                    if (Modifier.isPrivate(c.getModifiers())) {
                        try {
                        Constructor constructor = c.getDeclaredConstructor();
                        constructor.setAccessible(true);
                        List list = (List) constructor.newInstance();
                        list.get(0);
                        } catch (IndexOutOfBoundsException e) {
                            return c;
                        }
                        catch (NoSuchMethodException e) {
                            e.printStackTrace();
                        }
                        catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
        return null;
    }
}
