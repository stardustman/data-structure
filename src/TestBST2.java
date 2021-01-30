import tree.BST;

import java.util.ArrayList;
import java.util.Random;

public class TestBST2 {
    public static void main(String ...args){
        BST<Integer> bst = new BST<>();
        Random random = new Random();
        int n = 1000;

        for (int i = 0; i < n; i++) {
            bst.add(random.nextInt(1000));
        }
        ArrayList<Integer> nums = new ArrayList<>();
        while (!bst.isEmpty()){
            nums.add(bst.removeMin());
        }
        System.out.println(nums);
        for (int i = 1; i < nums.size() ; i++) {
            if(nums.get(i - 1) > nums.get(i)){
                throw new IllegalArgumentException("error");
            }
        }
        System.out.println("remove mim completed! ");


        BST<Integer> bst2 = new BST<>();
        for (int i = 0; i < n; i++) {
            int r = random.nextInt(1000);
            bst2.add(r);
            System.out.println(r);
        }
        ArrayList<Integer> nums2 = new ArrayList<>();
        while (!bst2.isEmpty()){
            nums2.add(bst2.removeMax());
        }
        System.out.println(nums2);
        for (int i = 1; i < nums2.size() ; i++) {
            if(nums2.get(i - 1) < nums2.get(i)){
                throw new IllegalArgumentException("error");
            }
        }
        System.out.println("remove max completed! ");
    }



}
