public interface Stack<E> {
    int getSize();
    void push(E e);
    E pop();
    E peek();
    boolean isEmpty();
}
