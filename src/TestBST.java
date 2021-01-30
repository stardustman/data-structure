import tree.BST;
public class TestBST {
    public static void main(String ...args){
        BST<Integer> bst = new BST<>();
        int[] nums = {5,3,6,8,4,2};
        for (int i = 0; i < nums.length; i++) {
            bst.add(nums[i]);
        }
        bst.preOrder();
        System.out.println(bst);
        bst.inOrder();
        bst.postOrder();
        bst.preOrderNR();
        bst.levelOrder();
        System.out.println(bst.minimum());
        System.out.println(bst.minimumNR());
        System.out.println(bst.maximum());
        System.out.println(bst.maximumNR());
    }
}
