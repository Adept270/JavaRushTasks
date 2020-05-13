package com.javarush.task.task33.task3310.strategy;

import java.io.Serializable;

public class Entry implements Serializable {
    Long key;
    String value;
    Entry next;
    int hash;

    public Entry(int hash, Long key, String value, Entry next) {
        this.key = key;
        this.value = value;
        this.next = next;
        this.hash = hash;
    }

    public Long getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }

    @Override
    public int hashCode() {
        return key.hashCode() ^ value.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (o instanceof Entry) {
            Entry e = (Entry) o;
            boolean test1 = getKey().equals(((Entry)o).getKey());
            boolean test2 = getValue().equals(((Entry)o).getValue());

            return test1 == test2;
        }
        return false;
    }

    @Override
    public String toString() {
        return key + "=" + value;
    }
}
