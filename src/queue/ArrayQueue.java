import array.Array;
import queue.Queue;

public class ArrayQueue<E> implements Queue<E> {
    private Array<E> array;

    public ArrayQueue(int capacity) {
        array = new Array(10);
    }

    public ArrayQueue() {
        array = new Array<E>();
    }

    @Override
    public void enqueue(E e) {
        array.addLast(e);
    }

    @Override
    public E dequeue() {
        return array.removeFirst();
    }

    @Override
    public E getFront() {
        return array.getFirst();
    }

    @Override
    public int getSize() {
        return array.getSize();
    }

    @Override
    public boolean isEmpty() {
        return array.isEmpty();
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append("queue front: [");
        for (int i = 0; i < getSize(); i++) {
            res.append(array.get(i));
            if(i != getSize() - 1)
                res.append(", ");
        }
        res.append("] tail");
        return res.toString();
    }
}
