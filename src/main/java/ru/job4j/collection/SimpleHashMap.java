package ru.job4j.collection;

import java.util.*;

public class SimpleHashMap<K, V> implements Iterable<K> {
    private int size = 10;
    private Node<K, V>[] table = new Node[size];
    private Node<K, V>[] tab;
    private int modCount = 0;
    private int count = 0;

    public boolean insert(K key, V value) {
        if (count == table.length) {
            size += 10;
            tab = table;
            table = new Node[size];
            for (Node<K, V> kvNode : tab) {
                int index = (size - 1) & kvNode.hash;
                table[index] = kvNode;
            }
            tab = new Node[size];
        }
        int hashNewNode = hash(key);
        int index = (size - 1) & hashNewNode;
        if (table[index] == null && !contains(key)) {
            table[index] = new Node<>(hashNewNode, key, value);
            count++;
            modCount++;
        } else {
            return false;
        }
        return true;
    }

    public V get(K key) {
        int hashKey = hash(key);
        int indexKey = (size - 1) & hashKey;
        if (table[indexKey] == null) {
            throw new NoSuchElementException();
        }
        return table[indexKey].value;
    }

    public boolean delete(K key) {
        int hashKey = hash(key);
        int indexKey = (size - 1) & hashKey;
        if (hashKey == table[indexKey].hash) {
            table[indexKey] = null;
            count--;
            modCount--;
        } else {
            throw new NoSuchElementException();
        }
        return true;
    }

    public boolean contains(K key) {
        for (Node<K, V> node : table) {
            if (node != null) {
                if (Objects.equals(node.key, key)) {
                    return true;
                }
            }
        }
        return false;
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
                return (K) table[point++];
            }
        };
    }

    private static class Node<K, V> {
        private int hash;
        K key;
        V value;

        public Node(int hash, K key, V value) {
            this.hash = hash;
            this.key = key;
            this.value = value;
        }
    }
}
