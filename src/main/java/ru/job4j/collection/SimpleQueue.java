package ru.job4j.collection;

import java.util.NoSuchElementException;

public class SimpleQueue<T> {
    private final SimpleStack<T> in = new SimpleStack<>();
    private final SimpleStack<T> out = new SimpleStack<>();

    public T poll() {
        T revOut = out.pop();
        if (revOut == null) {
            while (true) {
                T revIn = in.pop();
                if (revIn != null) {
                    out.push(revIn);
                } else {
                    revOut = out.pop();
                    break;
                }
            }
        }
        if (revOut == null) {
            throw new NoSuchElementException();
        }
        return revOut;
    }

    public void push(T value) {
        in.push(value);
    }
}
