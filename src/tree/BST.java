package tree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class BST<E extends Comparable<E>> { // E extends Comparable<E> 视为一个 UnknownTypeComparable
    private class Node{
        public E e;
        public Node left, right;

        public Node(E e){
            this.e = e;
            left = null;
            right = null;
        }
    }

    private Node root;
    private int size;

    public BST(){
        root = null;
        size = 0;
    }

    public int size(){
        return size;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    // 向 bst 中添加新的元素
    public void add(E e){
        root = add(root, e);
    }

    // 返回插入新节点后二分搜索树的根
    private Node add(Node node, E e){
       if(node == null){
           size++;
           return new Node(e);
       }
       if(e.compareTo(node.e) < 0){
           node.left = add(node.left, e);
       }
       if(e.compareTo(node.e) > 0){
           node.right = add(node.right,e);
       }
       return node;
    }

    public boolean contains(E e){
        return contains(root,e);
    }
    // 以 node 为根的节点是否包括 e
    private boolean contains(Node node, E e){
        if(node == null)
            return false;
        if(e.compareTo(node.e) == 0)
            return true;
        else if(e.compareTo(node.e) < 0)
            return contains(node.left,e);
        else //if(e.compareTo(node.e) > 0)
            return contains(node.right,e);
    }

    // 深度优先遍历
    public void preOrder(){
        preOrder(root);
    }
    // 前序遍历以 node 为 root 的二分搜索树
    private void preOrder(Node node){
        if(node == null)
            return;
        // 等价于 if(node != null)
        System.out.println(node.e); // root
        preOrder(node.left); // left subtree
        preOrder(node.right); // right subtree
    }

    public void preOrderNR(){
        Stack<Node> stack = new Stack<>();
        stack.push(root);
        while(!stack.isEmpty()){
            Node cur = stack.pop();
            System.out.println(cur.e);
            // 先右
            if(cur.right != null)
                stack.push(cur.right);
            if(cur.left != null)
                stack.push(cur.left);
        }

    }



    // 中序遍历  left root right
    public void inOrder(){
        inOrder(root);
    }

    private void inOrder(Node node){
        if(node == null)
            return;
        inOrder(node.left);
        System.out.println(node.e);
        inOrder(node.right);
    }

    // 后序遍历 left right root, 内存管理，先释放子节点，再释放本身
    public void postOrder(){
        postOrder(root);
    }
    private void postOrder(Node node){
        if(node == null)
            return;
        postOrder(node.left);
        postOrder(node.right);
        System.out.println(node.e);
    }

    // 层序遍历, 广度优先遍历
    public void levelOrder(){
        Queue<Node> q = new LinkedList<Node>();
        q.add(root);
        while (!q.isEmpty()){
            Node cur = q.remove();
            System.out.println(cur.e);
            if(cur.left != null)
                q.add(cur.left);
            if(cur.right != null)
                q.add(cur.right);
        }
    }

    // 最小元素
    public E minimum(){
        if(size == 0)
            throw new IllegalArgumentException("BST is empty");
        return minimum(root).e;
    }

    private Node minimum(Node node){
        if(node.left == null)
            return node;
        return minimum(node.left);
    }

    public E minimumNR(){
        if(size == 0)
            throw new IllegalArgumentException("BST is empty");
        Node node = root;
        while (node.left != null){
            node = node.left;
        }
        return node.e;
    }


    // 最大元素
    public E maximum(){
        if(size == 0)
            throw new IllegalArgumentException("BST is empty");
        return maximum(root).e;
    }

    private Node maximum(Node node){
        if(node.right == null)
            return node;
        return maximum(node.right);
    }

    public E maximumNR(){
        if(size == 0)
            throw new IllegalArgumentException("BST is empty");
        Node node = root;
        while (node.right != null){
            node = node.right;
        }
        return node.e;
    }

    // 删除 bst 中最小值，并返回该值
    public E removeMin(){
        E ret = minimum();
        root = removeMin(root);
        return ret;
    }

    // 删除以 node 为根的 bst 的最小节点
    // 返回删除节点后新的 bst 的根
    private Node removeMin(Node node){
       if(node.left == null){
           Node rightNode = node.right;
           node.right = null;
           size--;
           return rightNode; // 新的 root
       }
       node.left = removeMin(node.left);
       return node;
    }


    // 删除 bst 中最大值，并返回该值
    public E removeMax(){
        E ret = maximum();
        root = removeMax(root);
        return ret;
    }

    // 删除以 node 为根的 bst 的最大节点
    // 返回删除节点后新的 bst 的根
    private Node removeMax(Node node){
        if(node.right == null){
            Node leftNode = node.left;
            node.left = null;
            size--;
            return leftNode; // 新的 root
        }
        node.right = removeMax(node.right);
        return node;
    }

    // 从 bst 中删除元素值为 e 的节点
    public void remove(E e){
        root = remove(root, e);
    }

    // 删除以 node 为根的 bst 中值为 e 的节点
    // 返回删除节点后新的 bst 的根
    private Node remove(Node node, E e){
        if(node == null)
            return null;
        if(e.compareTo(node.e) < 0){
            node.left = remove(node.left, e);
            return node;
        }
        else if(e.compareTo(node.e) > 0){
            node.right = remove(node.right,e);
            return node;
        }
        else { // e == node.e

            // 待删除节点左子树为空
            if(node.left == null){
                Node rightNode = node.right;
                node.right = null;
                size--;
                return rightNode;
            }
            // 待删除节点右子树为空
            if(node.right == null){
                Node leftNode = node.left;
                node.left = null;
                size--;
                return leftNode;
            }
            // 左右子树都不为空
            // 找到该节点右子树中的最小值，为被删除节点的后继节点
            Node successor = minimum(node.right);
            // 删除该 successor 节点
            successor.right = removeMin(node.right);
            // 继承被删除节点的左子树
            successor.left = node.left;
            node.left = node.right = null;
            return successor;

            // predecessor
        }

    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        generateBSTString(root, 0, res);
        return res.toString();
    }

    private void generateBSTString(Node node, int depth, StringBuilder res){
        if(node == null){
            res.append(generateDepthString(depth) + "null\n");
            return;
        }
        res.append(generateDepthString(depth) + node.e + "\n");
        generateBSTString(node.left, depth + 1, res);
        generateBSTString(node.right, depth + 1, res);

    }

    private String generateDepthString(int depth){
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < depth; i++) {
            res.append("--");
        }
        return res.toString();
    }
}
