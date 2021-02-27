package ru.job4j.generics;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;

import org.junit.Before;
import org.junit.Test;

public class SimpleArrayTest {
    private SimpleArray<Integer> simpleArray;

    @Before
    public void setUp() {
        simpleArray = new SimpleArray<>(
                new Integer[]{1, 2, 3, null, null});
    }

    @Test
    public void addTest() {
        SimpleArray<Integer> res = new SimpleArray<>(
                new Integer[]{1, 2, 3, 4, null}
        );
        simpleArray.add(4);
        assertThat(simpleArray, is(res));
    }

    @Test
    public void setTest() {
        SimpleArray<Integer> res = new SimpleArray<>(
                new Integer[]{1, 2, 10, null, null}
        );
        simpleArray.set(2, 10);
        assertThat(simpleArray, is(res));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void setTestException() {
        simpleArray.set(5, 10);
    }

    @Test
    public void removeTest() {
        SimpleArray<Integer> res = new SimpleArray<>(
                new Integer[]{1, 3, null, null, null}
        );
        simpleArray.remove(1);
        assertThat(simpleArray, is(res));
    }

    @Test
    public void getTest() {
        Integer res = simpleArray.get(2);
        assertThat(res, is(3));
    }
}