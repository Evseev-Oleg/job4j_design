package ru.job4j.generics;

import java.util.Optional;

public interface Store<T extends Base> {
    void add(T model);

    boolean replace(String id, T model);

    boolean delete(String id);

    Optional<T> findById(String id);
}
