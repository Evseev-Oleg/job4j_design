package ru.job4j.generics;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;

import org.junit.Test;

public class SimpleArrayTest {

    @Test
    public void simpleTest() {
        SimpleArray<Integer> simple = new SimpleArray<>(new Integer[4]);
        simple.add(1);
        simple.add(2);
        assertThat(simple, is(new SimpleArray<>(new Integer[]{1, 2, null, null})));
        simple.set(1, 5);
        assertThat(simple, is(new SimpleArray<>(new Integer[]{1, 5, null, null})));
        assertThat(simple.get(0), is(1));
        simple.remove(0);
        assertThat(simple, is(new SimpleArray<>(new Integer[]{5, null, null, null})));
    }
}