package ru.job4j.collection;

import java.util.NoSuchElementException;

public class SimpleStack<T> {
    private final ForwardLinked<T> linked = new ForwardLinked<>();

    public T pop() {
        isEmpty(linked);
        return linked.deleteFirst();
    }

    public void push(T value) {
        linked.addFirst(value);
    }

    public void isEmpty(ForwardLinked<T> lin){
        if(lin.getHead() == null){
            throw new NoSuchElementException();
        }
    }
}
