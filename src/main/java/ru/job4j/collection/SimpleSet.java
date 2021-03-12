package ru.job4j.collection;

import java.util.Iterator;
import java.util.Objects;

public class SimpleSet<T> implements Iterable<T> {
    private final SimpleArray<T> simpleArray = new SimpleArray<>();

    public boolean add(T model) {
        if (contains(model)) {
            return false;
        }
        simpleArray.add(model);
        return true;
    }

    public boolean contains(T value) {
        for (T val : simpleArray) {
            if (Objects.equals(val, value)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Iterator<T> iterator() {
        return simpleArray.iterator();
    }
}
