package ru.job4j.collection;

import java.util.*;

public class SimpleHashMap<K, V> implements Iterable<K> {
    final double CUFF = 0.75d;
    private int size = 16;
    private Node<K, V>[] table = new Node[size];
    private Node<K, V>[] tab;
    private int modCount = 0;
    private int count = 0;

    public boolean insert(K key, V value) {
        if (count == size * CUFF) {
            increaseArray();
        }
        int hashNewNode = hash(key);
        int index = (size - 1) & hashNewNode;
        if (table[index] == null) {
            table[index] = new Node<>(hashNewNode, key, value);
            count++;
            modCount++;
        } else {
            return false;
        }
        return true;
    }

    public V get(K key) {
        V res = null;
        int hashKey = hash(key);
        int indexKey = (size - 1) & hashKey;
        if (table[indexKey] == null) {
            return res;
        }
        if (hashKey == table[indexKey].hash && table[indexKey].key.equals(key)) {
            res = table[indexKey].value;
        }
        return res;
    }

    public boolean delete(K key) {
        boolean res = false;
        int hashKey = hash(key);
        int indexKey = (size - 1) & hashKey;
        if (table[indexKey] == null) {
            return res;
        }
        if (hashKey == table[indexKey].hash && table[indexKey].key.equals(key)) {
            table[indexKey] = null;
            count--;
            modCount--;
            res = true;
        }
        return res;
    }

    public void increaseArray() {
        size *= 2;
        tab = table;
        table = new Node[size];
        for (Node<K, V> kvNode : tab) {
            if (kvNode == null) {
                continue;
            }
            int index = (size - 1) & kvNode.hash;
            table[index] = kvNode;
        }
        tab = new Node[size];
    }

    public static int hash(Object key) {
        int h = key.hashCode();
        return h ^ h >>> 16;
    }


    @Override
    public Iterator<K> iterator() {
        return new Iterator<>() {
            private final int expectedModCount = modCount;
            private int point = 0;

            @Override
            public boolean hasNext() {
                return point < count;
            }

            @Override
            public K next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                while (table[point] == null) {
                        point++;
                }
                return (K) table[point];
            }
        };
    }

    private static class Node<K, V> {
        private final int hash;
        K key;
        V value;

        public Node(int hash, K key, V value) {
            this.hash = hash;
            this.key = key;
            this.value = value;
        }
    }
}
