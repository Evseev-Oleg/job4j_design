package ru.job4j.collection;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleLinkedList<E> implements Iterable<E> {
    private Node<E> nodeHead;
    private Node<E> nodeTail;
    private int count = 0;
    private int modCount = 0;

    public void add(E value) {
        Node<E> node = new Node<>(value);
        if (nodeHead == null) {
            nodeHead = node;
        } else {
            nodeTail.next = node;
            node.prev = nodeTail;
        }
        nodeTail = node;
        count++;
        modCount++;
    }

    public E get(int index) {
        Objects.checkIndex(index, count);
        Node<E> result = nodeHead;
        for (int i = 0; i < index; i++) {
            result = result.next;
        }
        return result.data;
    }

    private static class Node<E> {
        E data;
        private Node<E> next;
        private Node<E> prev;

        public Node(E data) {
            this.data = data;
        }
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<>() {
            private int expectedModCount = modCount;
            private Node<E> result = nodeHead;

            @Override
            public boolean hasNext() {
                return result != null;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }

                E value = result.data;
                result = result.next;
                return value;
            }
        };
    }

    public static void main(String[] args) {
        SimpleLinkedList<String> simple = new SimpleLinkedList<>();
        simple.add("a");
        simple.add("b");
        simple.add("c");
        for (int i = 1; i < simple.count; i++) {
            System.out.println(simple.get(i));
        }
    }
}
