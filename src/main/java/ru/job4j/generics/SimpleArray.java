package ru.job4j.generics;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Objects;

public class SimpleArray<T> implements Iterable<T> {
    private final T[] arr;

    public SimpleArray(T[] arr) {
        this.arr = arr;
    }

    public T[] getArr() {
        return arr;
    }

    void add(T model) {
        for (int i = 0; i < arr.length; i++) {
            if (!Objects.nonNull(arr[i])) {
                arr[i] = model;
                break;
            }
        }
    }

    void set(int index, T model) {
        Objects.checkIndex(index, arr.length);
        arr[index] = model;
    }

    void remove(int index) {
        Objects.checkIndex(index, arr.length);
        System.arraycopy(arr, index + 1, arr, index, arr.length - index - 1);
    }

    T get(int index) {
        Objects.checkIndex(index, arr.length);
        return arr[index];
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
