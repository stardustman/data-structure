package tree.redblack;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class RBTree<K extends Comparable<K>, V> {
    private static final boolean RED = true;
    private static final boolean BLACK = false;
    private class Node{
        public K key;
        public V value;
        public Node left, right;
        public boolean color;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            this.left = null;
            this.right = null;
            this.color = RED;
        }
    }
    private Node root;
    private int size;

    public RBTree() {
        root = null;
        size = 0;
    }

    public int getSize(){
        return size;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public boolean isRed(Node node){
        if(node == null){
            return BLACK;
        }
        return node.color;
    }

    public void add(K key, V value){
        root = add(root, key, value);
        root.color = BLACK;
    }

    /**
     * 向以 node 为根的红黑树中插入元素(key, value)
     * @param node
     * @param key
     * @param value
     * @return
     */
    private Node add(Node node, K key, V value){
        if(node == null){
            size++;
            return new Node(key, value); // 默认红色节点
        }
        if(key.compareTo(node.key) < 0){
            node.left = add(root.left, key, value);
        } else if(key.compareTo(node.key) > 0){
            node.right = add(root.right, key, value);
        } else{
            node.value = value;
        }
        // 是否要左旋转
        if(isRed(node.right) && !isRed(node.left)){
            node = leftRotate(node);
        }
        // 是否要右旋转
        if(isRed(node.left) && isRed(node.left.left)){
            node = rightRotate(node);
        }
        // 是否要颜色翻转
        if(isRed(node.left) && isRed(node.right)){
            flipColor(node);
        }


        return node;
    }

    /**
     *    node                                x
     *   /   \       leftRotate(node)       /   \
     *  T1   x(Red)-------------------->  node  T3
     *      / \                           /  \
     *     T2 T3                         T1  T2
     * @return
     */
    private Node leftRotate(Node node){
        Node x = node.right;
        // left rotate
        node.right = x.left;
        x.left = node;
        // 变色
        x.color = node.color;
        node.color = RED;
        return x;
    }

    /**
     *    node                                 x
     *   /   \       rightRotate(node)       /  \
     *  x   T2     -------------------->    y   node
     * / \                                      /  \
     * y T1                                    T1  T2
     * @return
     */
    private Node rightRotate(Node node){
        Node x = node.left;
        // 右旋转
        node.left = x.right;
        x.right = node;

        // 颜色
        x.color = node.color;
        node.color = RED;

        return x;
    }

    /**
     * 颜色反转
     * @param node node 为 root
     */
    private void flipColor(Node node){
        node.color = RED;
        node.left.color = BLACK;
        node.right.color = RED;
    }


    public boolean contains(K key){
        return contains(root,key);
    }
    // 以 node 为根的节点是否包括 e
    private boolean contains(Node node, K key){
        if(node == null)
            return false;
        if(key.compareTo(node.key) == 0)
            return true;
        else if(key.compareTo(node.key) < 0)
            return contains(node.left,key);
        else //if(e.compareTo(node.e) > 0)
            return contains(node.right,key);
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
        System.out.println(node.key); // root
        preOrder(node.left); // left subtree
        preOrder(node.right); // right subtree
    }

    public void preOrderNR(){
        Stack<Node> stack = new Stack<>();
        stack.push(root);
        while(!stack.isEmpty()){
            Node cur = stack.pop();
            System.out.println(cur.key);
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
        System.out.println(node.key);
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
        System.out.println(node.key);
    }

    // 层序遍历, 广度优先遍历
    public void levelOrder(){
        Queue<Node> q = new LinkedList<Node>();
        q.add(root);
        while (!q.isEmpty()){
            Node cur = q.remove();
            System.out.println(cur.key);
            if(cur.left != null)
                q.add(cur.left);
            if(cur.right != null)
                q.add(cur.right);
        }
    }

    // 最小元素
    public K minimum(){
        if(size == 0)
            throw new IllegalArgumentException("BST is empty");
        return minimum(root).key;
    }

    private Node minimum(Node node){
        if(node.left == null)
            return node;
        return minimum(node.left);
    }

    public K minimumNR(){
        if(size == 0)
            throw new IllegalArgumentException("BST is empty");
        Node node = root;
        while (node.left != null){
            node = node.left;
        }
        return node.key;
    }


    // 最大元素
    public K maximum(){
        if(size == 0)
            throw new IllegalArgumentException("BST is empty");
        return maximum(root).key;
    }

    private Node maximum(Node node){
        if(node.right == null)
            return node;
        return maximum(node.right);
    }

    public K maximumNR(){
        if(size == 0)
            throw new IllegalArgumentException("BST is empty");
        Node node = root;
        while (node.right != null){
            node = node.right;
        }
        return node.key;
    }

    // 删除 bst 中最小值，并返回该值
    public K removeMin(){
        K ret = minimum();
        root = removeMin(root);
        return ret;
    }

    // 删除以 node 为根的 bst 的最小节点
    // 返回删除节点后新的 bst 的根？？？
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
    public K removeMax(){
        K ret = maximum();
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
    public void remove(K key){
        root = remove(root, key);
    }

    // 删除以 node 为根的 bst 中值为 e 的节点
    // 返回删除节点后新的 bst 的根
    private Node remove(Node node, K key){
        if(node == null)
            return null;
        if(key.compareTo(node.key) < 0){
            node.left = remove(node.left, key);
            return node;
        }
        else if(key.compareTo(node.key) > 0){
            node.right = remove(node.right,key);
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
        res.append(generateDepthString(depth) + node.key + "\n");
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
