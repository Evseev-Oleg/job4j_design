package ru.job4j.collection;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleArrayIterator<T> implements Iterator<T> {
    SimpleArray<T> simpleArray;
    private int expectedModCount = 0;

    @Override
    public boolean hasNext() {
        if (expectedModCount != simpleArray.getModCount()) {
            throw new ConcurrentModificationException();
        }
        return expectedModCount < simpleArray.getModCount();
    }

    @Override
    public T next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return simpleArray.getContainer()[expectedModCount++];
    }
}
