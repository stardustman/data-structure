import array.Array;
import queue.LoopQueue;
import queue.Queue;
import queue.ArrayQueue;
import stack.ArrayStack;

public class Main {

    public static void main(String[] args) {
        Array<Integer> arr = new Array(20);
        for (int i = 0; i < 20; i++) {
            arr.addLast(i);
        }
        System.out.println(arr.toString());

        arr.removeElement(10);
        System.out.println(arr.toString());
        System.out.println(arr.find(11));
        arr.addLast(100);
        arr.addLast(200);
        System.out.println(arr.toString());

        Array<Student> students = new Array<>(20);
        students.addLast(new Student("Alice",10));
        students.addLast(new Student("Bob",20));
        students.addLast(new Student("Dust",30));
        System.out.println(students);

        ArrayStack<Integer> stack = new ArrayStack<>(10);
        for (int i = 0; i < 10; i++) {
            stack.push(i);
        }
        System.out.println(stack);
        stack.pop();
        System.out.println(stack);

        Queue<Integer> queue = new ArrayQueue<>(10);
        for (int i = 0; i < 10; i++) {
            queue.enqueue(i);
        }
        System.out.println(queue);
        queue.dequeue();
        System.out.println(queue);

        Queue loopQueue = new LoopQueue(10);
        for (int i = 0; i < 10; i++) {
            loopQueue.enqueue(i);
        }
        System.out.println(loopQueue);
        loopQueue.enqueue(100);
        System.out.println(loopQueue);
        loopQueue.dequeue();
        loopQueue.dequeue();
        loopQueue.dequeue();
        loopQueue.dequeue();
        loopQueue.dequeue();
        loopQueue.dequeue();
        loopQueue.dequeue();
        loopQueue.dequeue();
        loopQueue.dequeue();
        loopQueue.dequeue();

        System.out.println(loopQueue);

    }
}
