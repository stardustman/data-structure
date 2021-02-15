import tree.avl.AVLTree;

import java.util.Random;

public class TestAVLTree {
    public static void main(String ...args){
        AVLTree<Integer,String> avlTree = new AVLTree<>();
        Random random = new Random();
        int size = 1000;
        int arr[] = new int[size];
        for (int i = 0; i < size; i++) {
            int a = random.nextInt(1000);
            arr[i] = a;
            avlTree.add(a, "abc");
        }
        System.out.println(avlTree.isBalanced());
        for (int i = 0; i < size; i++) {
            avlTree.remove(arr[i]);
            if(!avlTree.isBalanced()){
                System.out.println("worng");
                break;
            }
        }

    }
}
