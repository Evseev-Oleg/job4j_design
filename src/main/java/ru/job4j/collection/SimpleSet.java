package ru.job4j.collection;

import java.util.Arrays;
import java.util.Iterator;

public class SimpleSet<T> implements Iterable<T> {
    private final SimpleArray<T> simpleArray = new SimpleArray<>();

    public boolean add(T model) {
        for (int i = 0; i < simpleArray.getCount(); i++) {
            if (simpleArray.getContainer()[i].equals(model)) {
                return false;
            }
        }
        simpleArray.add(model);
        return true;
    }

    @Override
    public Iterator<T> iterator() {
        return simpleArray.iterator();
    }

    @Override
    public String toString() {
        return Arrays.toString(simpleArray.getContainer());
    }
}
