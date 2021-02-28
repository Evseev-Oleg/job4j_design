package ru.job4j.generics;

import java.util.ArrayList;
import java.util.List;

public final class MemStore<T extends Base> implements Store<T> {
    private final List<T> mem = new ArrayList<>();

    @Override
    public void add(T model) {
        int rsl = findIndex(model.getId());
        if (rsl == -1) {
            mem.add(model);
        }
    }

    @Override
    public boolean replace(String id, T model) {
        int rsl = findIndex(id);
        if (rsl != -1) {
            mem.set(rsl, model);
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(String id) {
        int rsl = findIndex(id);
        if (rsl != -1) {
            mem.remove(rsl);
            return true;
        }
        return false;
    }

    @Override
    public T findById(String id) {
        int rsl = findIndex(id);
        return rsl != -1 ? mem.get(rsl) : null;
    }

    public int findIndex(String id) {
        int rsl = -1;
        for (int i = 0; i < mem.size(); i++) {
            if (mem.get(i).getId().equals(id)) {
                return i;
            }
        }
        return rsl;
    }
}
