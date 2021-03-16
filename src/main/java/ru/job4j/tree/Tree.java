package ru.job4j.tree;

import java.util.*;
import java.util.function.Predicate;

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
        return findByPredicate(x -> x.value.equals(value));
    }

    public boolean isBinary() {
        Optional<Node<E>> rsl = findByPredicate(x -> x.children.size() > 2);
        return rsl.isEmpty();
    }


    private Optional<Node<E>> findByPredicate(Predicate<Node<E>> condition) {
        Optional<Node<E>> rsl = Optional.empty();
        Queue<Node<E>> data = new LinkedList<>();
        data.offer(this.root);
        while (!data.isEmpty()) {
            Node<E> el = data.poll();
            if (condition.test(el)) {
                rsl = Optional.of(el);
                break;
            }
            data.addAll(el.children);
        }
        return rsl;
    }
}
