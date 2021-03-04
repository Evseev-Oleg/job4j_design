package ru.job4j.collection;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

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
            private int point = 0;

            @Override
            public boolean hasNext() {
                return point < count;
            }

            @Override
            public E next() {
                Node<E> result = nodeHead;
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                point++;
                return result.data;
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
