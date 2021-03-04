package ru.job4j.collection;

import java.util.*;

public class SimpleArray<T> implements Iterable<T> {
    private T[] container = (T[]) new Object[10];
    private int modCount = 0;
    private int count = 0;

    public T get(int index) {
        Objects.checkIndex(index, count);
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
            private int expectedModCount = modCount;
            private int point = 0;

            @Override
            public boolean hasNext() {
                return point < count;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return container[point++];
            }
        };
    }

    public static void main(String[] args) {
        SimpleArray<String> simpleArray = new SimpleArray<>();
        simpleArray.add("a");
        simpleArray.add("b");
        simpleArray.add("c");
        for (int i = 1; i < simpleArray.count; i++) {
            System.out.println(simpleArray.get(i));
        }
    }
}
