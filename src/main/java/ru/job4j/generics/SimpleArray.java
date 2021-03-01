package ru.job4j.generics;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Objects;

public class SimpleArray<T> implements Iterable<T> {
    private final T[] arr;
    private int size = 0;

    public SimpleArray(T[] arr) {
        this.arr = arr;
    }

    public T[] getArr() {
        return arr;
    }

    void add(T model) {
        arr[size++] = model;
    }

    void set(int index, T model) {
        Objects.checkIndex(index, size);
        arr[index] = model;
    }

    void remove(int index) {
        Objects.checkIndex(index, size);
        System.arraycopy(arr, index + 1, arr, index, arr.length - index - 1);
        size--;
    }

    T get(int index) {
        Objects.checkIndex(index, size);
        return arr[index];
    }

    public int getSize() {
        return size;
    }

    @Override
    public Iterator<T> iterator() {
        return new SimpleArrayIterator<>();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SimpleArray<?> that = (SimpleArray<?>) o;
        return Arrays.equals(arr, that.arr);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(arr);
    }
}
