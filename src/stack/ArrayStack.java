package stack;
import array.Array;


public class ArrayStack<E> implements Stack<E> {
    Array<E> array;

    public ArrayStack(int capacity) {
        this.array = new Array<>(capacity);
    }

    public ArrayStack() {
        this.array = new Array<>(10);
    }

    @Override
    public int getSize() {
        return array.getSize();
    }

    @Override
    public void push(E e) {
        array.addLast(e);
    }

    @Override
    public E pop() {
        return array.removeLast();
    }

    @Override
    public E peek() {
        return array.getLast();
    }

    @Override
    public boolean isEmpty() {
        return array.isEmpty();
    }

    public int getCapacity(){
        return array.getCapacity();
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append("Stack [");
        for (int i = 0; i < getSize(); i++) {
            res.append(array.get(i));
            if(i != getSize() - 1)
                res.append(", ");
        }
        res.append("] top");
        return res.toString();
    }
}
