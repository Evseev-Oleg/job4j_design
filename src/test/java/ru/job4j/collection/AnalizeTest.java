package ru.job4j.collection;

import org.junit.Test;

import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class AnalizeTest {
    @Test
    public void whenAdded() {
        Analize analize = new Analize();
        Analize.Info info = analize.diff(List.of(new Analize.User(1, "A"),
                new Analize.User(2, "B"),
                new Analize.User(3, "C")),
                List.of(new Analize.User(1, "A"),
                        new Analize.User(2, "B"),
                        new Analize.User(3, "C"),
                        new Analize.User(4, "D")));
        int res = info.added;
        assertThat(res, is(1));
    }

    @Test
    public void whenChanged() {
        Analize analize = new Analize();
        Analize.Info info = analize.diff(List.of(new Analize.User(1, "A"),
                new Analize.User(2, "B"),
                new Analize.User(3, "C")),
                List.of(new Analize.User(1, "AAA"),
                        new Analize.User(2, "B"),
                        new Analize.User(3, "CCC"),
                        new Analize.User(4, "D")));
        int res = info.changed;
        assertThat(res, is(2));
    }

    @Test
    public void whenDelete() {
        Analize analize = new Analize();
        Analize.Info info = analize.diff(List.of(new Analize.User(1, "A"),
                new Analize.User(2, "B"),
                new Analize.User(3, "C")),
                List.of(new Analize.User(1, "AAA"),
                        new Analize.User(3, "CCC"),
                        new Analize.User(4, "D")));
        int res = info.deleted;
        assertThat(res, is(1));
    }
}