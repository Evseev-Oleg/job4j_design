package ru.job4j.collection;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ForwardLinked<T> implements Iterable<T> {
    private Node<T> head;
    private int count = 0;


    public T deleteLast() {
        if (head == null) {
            throw new NoSuchElementException();
        }
        if (count == 1) {
            T result = head.value;
            head = null;
            count--;
            return result;
        }
        Node<T> variableNode = head;
        T result = null;
        for (int i = 0; i < count - 1; i++) {
            if (i == count - 2) {
                result = variableNode.value;
            }
            variableNode = variableNode.next;
        }
        variableNode.next = null;
        count--;
        return result;
    }

    public T deleteFirst() {
        T res;
        if (head == null) {
            throw new NoSuchElementException();
        }
        res = head.value;
        head = head.next;
        count--;
        return res;
    }

    public void add(T value) {
        Node<T> node = new Node<>(value, null);
        if (head == null) {
            head = node;
            count++;
            return;
        }
        Node<T> tail = head;
        while (tail.next != null) {
            tail = tail.next;
        }
        tail.next = node;
        count++;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            Node<T> node = head;

            @Override
            public boolean hasNext() {
                return node != null;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                T value = node.value;
                node = node.next;
                return value;
            }
        };
    }

    private static class Node<T> {
        T value;
        Node<T> next;

        public Node(T value, Node<T> next) {
            this.value = value;
            this.next = next;
        }
    }
}
