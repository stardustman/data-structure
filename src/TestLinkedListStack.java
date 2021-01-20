import java.util.Random;

public class TestLinkedListStack {
    private static double testLinkedListStack(Stack<Integer>s,int opCount){
        long startTime = System.nanoTime();
        Random random = new Random();
        for (int i = 0; i < opCount ; i++) {
            s.push(random.nextInt(Integer.MAX_VALUE));
        }
        for (int i = 0; i < opCount; i++) {
            s.pop();
        }

        long endTime = System.nanoTime();
        return (endTime - startTime)/1000000000.0;
    }
    public static void main(String[] args){
        Stack<Integer> stack = new LinkedListStack<Integer>();
        double times = testLinkedListStack(stack,100000000);
        System.out.println(times);
        for (int i = 0; i < 10 ; i++) {
            stack.push(i);
            System.out.println(stack);
        }
    }
}
