package ru.job4j.generics;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleArrayIterator<T> implements Iterator<T> {
    SimpleArray<T> simpleArray;
    private int point = 0;

    @Override
    public boolean hasNext() {
        return point < simpleArray.getArr().length;
    }

    @Override
    public T next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return simpleArray.getArr()[point++];
    }
}
