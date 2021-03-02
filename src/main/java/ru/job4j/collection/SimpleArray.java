package ru.job4j.collection;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Objects;

public class SimpleArray<T> implements Iterable<T> {
    private Objects[] container = new Objects[10];
    private int modCount = 0;

    public SimpleArray() {
    }

    public T get(int index) {
        Objects.checkIndex(index, modCount);
        return (T) container[index];
    }

    public void add(T model) {
        if (modCount == container.length - 1) {
            container = Arrays.copyOf(container, container.length + 10);
        }
        container[modCount++] = (Objects) model;
    }

    public T[] getContainer() {
        return (T[]) container;
    }

    public int getModCount() {
        return modCount;
    }

    @Override
    public Iterator<T> iterator() {
        return new SimpleArrayIterator<>();
    }
}
