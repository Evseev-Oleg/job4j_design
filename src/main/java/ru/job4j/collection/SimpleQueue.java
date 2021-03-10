package ru.job4j.collection;

public class SimpleQueue<T> {
    SimpleStack<T> stack = new SimpleStack<>();
    private final SimpleStack<T> in = new SimpleStack<>();
    private final SimpleStack<T> out = new SimpleStack<>();

    public T poll() {
        if (stack.isEmpty(out)) {
            while (!stack.isEmpty(in)) {
                out.push(in.pop());
            }
        }
        return out.pop();
    }

    public void push(T value) {
        in.push(value);
    }
}
