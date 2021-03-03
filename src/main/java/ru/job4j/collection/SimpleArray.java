package ru.job4j.collection;

import java.util.*;

public class SimpleArray<T> implements Iterable<T> {
    private T[] container = (T[]) new Object[10];
    private int modCount = 0;
    private int count = 0;

    public T get(int index) {
        Objects.checkIndex(index, modCount);
        return (T) container[index];
    }

    public void add(T model) {
        if (count == container.length) {
            container = Arrays.copyOf(container, container.length + 10);
        }
        container[count++] = model;
        modCount++;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            private int expectedModCount = 0;

            @Override
            public boolean hasNext() {
                return expectedModCount < modCount;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                if (++expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return container[expectedModCount - 1];
            }
        };
    }
}
