package ru.job4j.collection;

import org.junit.Test;

import java.util.NoSuchElementException;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class SimpleHashMapTest {
    @Test
    public void whenTestInsertAndGet() {
        SimpleHashMap<String, Integer> map = new SimpleHashMap<>();
        map.insert("first", 1);
        map.insert("second", 2);
        map.insert("third", 3);
        map.insert("fourth", 4);
        int res = map.get("third");
        assertThat(res, is(3));
    }

    @Test(expected = NoSuchElementException.class)
    public void whenTestDeleteAndGet() {
        SimpleHashMap<String, Integer> map = new SimpleHashMap<>();
        map.insert("first", 1);
        map.insert("second", 2);
        map.insert("third", 3);
        map.insert("fourth", 4);
        map.delete("first");
        map.get("first");
    }
}