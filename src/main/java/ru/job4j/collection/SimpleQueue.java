package ru.job4j.collection;

import java.util.NoSuchElementException;

public class SimpleQueue<T> {
    private final SimpleStack<T> in = new SimpleStack<>();
    private final SimpleStack<T> out = new SimpleStack<>();

    public T poll() {
        T revOut;
        if (isEmpty((T) out.getLinked().getHead())) {
            while (true) {
                if (isEmpty((T) in.getLinked().getHead())) {
                    revOut = out.pop();
                    break;
                } else {
                    T revIn = in.pop();
                    out.push(revIn);
                }
            }
        } else {
            revOut = out.pop();
        }
        if (revOut == null) {
            throw new NoSuchElementException();
        }
        return revOut;
    }

    public void push(T value) {
        in.push(value);
    }

    public boolean isEmpty(T rev) {
        return rev == null;
    }
}
