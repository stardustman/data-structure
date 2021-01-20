import java.io.InputStream;
import java.util.Random;

public class TestQueue {
    private static double testQueue(Queue<Integer>q,int opCount){
        long startTime = System.nanoTime();
        Random random = new Random();
        for (int i = 0; i < opCount ; i++) {
            q.enqueue(random.nextInt(Integer.MAX_VALUE));
        }
        for (int i = 0; i < opCount; i++) {
            q.dequeue();
        }

        long endTime = System.nanoTime();
        return (endTime - startTime)/1000000000.0;
    }

    public static void main(String[] args){
        int opCount = 1000000;
        Queue<Integer> arrayQueue = new ArrayQueue<>();
        double t1 = testQueue(arrayQueue,opCount);
        System.out.println(t1 + "s");
        Queue<Integer> loopQueue = new LoopQueue<>();
        double t2 = testQueue(loopQueue,opCount);
        System.out.println(t2 + "s");
        //433.0624732s
        //0.0687068s
    }
}
