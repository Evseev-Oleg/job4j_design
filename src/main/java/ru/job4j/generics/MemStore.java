package ru.job4j.generics;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public final class MemStore<T extends Base> implements Store<T> {
    private final List<T> mem = new ArrayList<>();

    @Override
    public void add(T model) {
        Optional<T> res = findById(model.getId());
        if (!res.isPresent()) {
            mem.add(model);
        }
    }

    @Override
    public boolean replace(String id, T model) {
        Optional<T> res = findById(id);
        if (res.isPresent()) {
            mem.set(mem.indexOf(res.get()), model);
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(String id) {
        Optional<T> res = findById(id);
        if (res.isPresent()) {
            mem.remove(res.get());
            return true;
        }
        return false;
    }

    @Override
    public Optional<T> findById(String id) {
        Optional<T> rsl = Optional.empty();
        for (T t : mem) {
            if (t.getId().equals(id)) {
                return Optional.of(t);
            }
        }
        return rsl;
    }
}
