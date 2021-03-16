package ru.job4j.tree;

import java.util.*;

public class Tree<E> implements SimpleTree<E> {
    private final Node<E> root;

    Tree(final E root) {
        this.root = new Node<>(root);
    }

    @Override
    public boolean add(E parent, E child) {
        boolean res = false;
        Optional<Node<E>> par = findBy(parent);
        Optional<Node<E>> children = findBy(child);
        if (par.isPresent()) {
            if (children.isEmpty()) {
                Node<E> node = new Node<>(child);
                par.get().children.add(node);
                res = true;
            }
        }
        return res;
    }

    @Override
    public Optional<Node<E>> findBy(E value) {
        Optional<Node<E>> rsl = Optional.empty();
        Queue<Node<E>> data = new LinkedList<>();
        data.offer(this.root);
        while (!data.isEmpty()) {
            Node<E> el = data.poll();
            if (el.value.equals(value)) {
                rsl = Optional.of(el);
                break;
            }
            data.addAll(el.children);
        }
        return rsl;
    }
}
