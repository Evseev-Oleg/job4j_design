package ru.job4j.collection;

import org.junit.Test;

import java.util.Iterator;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class SimpleSetTest {
    @Test
    public void whenAdd() {
        SimpleSet<String> array = new SimpleSet<>();
        array.add("first");
        array.add("first");
        array.add("second");
        Iterator<String> it = array.iterator();
        assertThat(it.next(), is("first"));
        assertThat(it.next(), is("second"));
    }


}