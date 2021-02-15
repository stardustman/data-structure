package tree.avl;


import java.util.ArrayList;

public class AVLTree<K extends Comparable<K>, V>  {
    private class Node{
        public K key;
        public V value;
        public Node left, right;
        public int height; // 节点高度

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            this.left = null;
            this.right = null;
            this.height = 1; // 叶子节点高度为 1
        }
    }

    private Node root;
    private int size;

    public AVLTree() {
        this.root = null;
        this.size = 0;
    }

    // 获取节点高度
    private int getHeight(Node node){
        if(node == null){
            return 0;
        }
        return node.height;
    }

    /**
     * 获得 node 的平衡因子，左子树高度 - 右子树高度
     * @param node
     * @return
     */
    private int getBalanceFactor(Node node){
        if(node == null){
            return 0;
        }
        return getHeight(node.left) - getHeight(node.right);
    }

    // 判断该二叉树是不是一棵二分搜索树
    public boolean isBST(){
        ArrayList<K> keys = new ArrayList<>();
        inOrder(root, keys);
        for (int i = 1; i < keys.size(); i++) {
            if(keys.get(i - 1).compareTo(keys.get(i)) > 0){
                return false;
            }
        }
        return true;
    }

    /**
     * 中序遍历
     * @param node
     * @param keys
     */
    private void inOrder(Node node, ArrayList<K> keys){
        if(node == null){
            return;
        }
        inOrder(node.left, keys);
        keys.add(node.key);
        inOrder(node.right, keys);
    }

    public boolean isBalanced(){
        return isBalanced(root);
    }

    /**
     * 以 node 为根的二分搜索树是不是平衡的
     * @param node
     * @return
     */
    private boolean isBalanced(Node node){
        if(node == null){
            return true;
        }
        int balanceFactor = getBalanceFactor(node);
        if(Math.abs(balanceFactor) > 1){
            return false;
        }
        return isBalanced(node.left) && isBalanced(node.right);
    }

    /**       y
     *       / \      rightRotate(y)
     *      x   T4 ------------------->      x
     *     / \                             /  \
     *    z  T3                           z    y
     *   / \                            /  \  / \
     *  T1 T2                          T1 T2 T3 T4
     * @param y 失衡的节点
     * @return
     */
    private Node rightRotate(Node y){
        Node x = y.left;
        Node T3 = x.right;
        x.right = y;
        y.left = T3;
        // 更新节点 height，先更新 y 的值
        y.height = Math.max(getHeight(y.left),getHeight(y.right)) + 1;
        x.height = Math.max(getHeight(x.left),getHeight(x.right)) + 1;
        // 旋转后的 root
        return x;
    }

    /**       y
     *       / \      leftRotate(y)
     *      T1  x ------------------->        x
     *        /  \                          /  \
     *       T2  z                         y    z
     *          / \                      /  \  / \
     *         T3 T4                    T1 T2 T3 T4
     * @param y 失衡的节点
     * @return 平衡后的 root
     */
    private Node leftRotate(Node y){
        Node x = y.right;
        Node T2 = x.left;
        x.left = y;
        y.right = T2;
        // 更新节点 height，先更新 y 的值
        y.height = Math.max(getHeight(y.left),getHeight(y.right)) + 1;
        x.height = Math.max(getHeight(x.left),getHeight(x.right)) + 1;
        // 旋转后的 root
        return x;

    }


    public void add(K key, V value) {
        root = add(root,key,value);
    }

    /**
     *
     * @param node 向以 node 为根的二分搜索树中添加元素(key,value)
     * @param key
     * @param value
     * @return
     */
    private Node add(Node node, K key, V value){
        if(node == null){
            size++;
            return new Node(key,value); // 叶子节点，高度为 1
        }
        if(key.compareTo(node.key) < 0){
            node.left = add(node.left,key,value);
        }
        else if(key.compareTo(node.key) > 0){
            node.right = add(node.right,key,value);
        } else{ // key.compareTo(node.key) ==  0
            node.value = value;
        }
        // 更新当前 node height
        node.height = 1 + Math.max(getHeight(node.left),getHeight(node.right));
        // 该节点平衡因子
        int balanceFactor = getBalanceFactor(node);
        // 判断是否失衡了
        if(Math.abs(balanceFactor) > 1){
            System.out.println("unbalanced : " + balanceFactor);
        }
        // 平衡维护，以该节点为根的左子树比右子树高，该节点的左子节点(平衡的)也是如此
        // LL (node's left child's left)
        if(balanceFactor > 1 && getBalanceFactor(node.left) >= 0){
            return rightRotate(node);
        }
        // 平衡维护，以该节点为根的左子树比右子树低，该节点的右子节点(平衡的)也是如此
        // RR (node's right child's right)
        if(balanceFactor < -1 && getBalanceFactor(node.right) <= 0){
            return leftRotate(node);
        }
        // LR
        if(balanceFactor > 1 && getBalanceFactor(node.left) < 0){
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }
        // RL
        if(balanceFactor < -1 && getBalanceFactor(node.right) > 0){
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }
        // default
        return node;
    }



    // 返回以 node 为节点的 bst, key 所在的节点
    private Node getNode(Node node, K key){
        if(node == null){
            return  null;
        }
        if(key.compareTo(node.key) == 0){
            return node;
        }
        else if(key.compareTo(node.key) < 0){
            return getNode(node.left, key);
        }
        else { //if(key.compareTo(node.key) > 0)
            return getNode(node.right, key);
        }
    }

    public V remove(K key) {
        Node node = getNode(root,key);
        if(node != null){
            remove(root, key);
            return node.value;
        }
        return null;
    }

    // 删除以 node 为 bst root 中键位 key 的节点。
    // 返回删除节点后,调整平衡后新的 bst 的根
    private Node remove(Node node, K key){
        if(node == null){
            return null;
        }
        Node retNode;
        if(key.compareTo(node.key) < 0){
            node.left = remove(node.left, key);
            retNode = node;
            //return node;
        } else if(key.compareTo(node.key) > 0){
            node.right = remove(node.right, key);
            retNode = node;
            //return node;
        } else{
            // 待删除节点左子树为空
            if(node.left == null){
                Node rightNode = node.right;
                node.right = null;
                size--;
                retNode = rightNode;
            }
            // 待删除节点右子树为空
            else if(node.right == null){
                Node leftNode = node.left;
                node.left = null;
                size--;
                retNode = leftNode;
            }
            else {
                // 左右子树都不为空
                // 找到该节点右子树中的最小值，为被删除节点的后继节点
                Node successor = minimum(node.right);
                // 删除该 successor 节点
                //successor.right = removeMin(node.right); 这里没有调整平衡，不能直接使用
                successor.right = remove(node.right, successor.key);
                // 继承被删除节点的左子树
                successor.left = node.left;
                node.left = node.right = null;
                retNode = successor;
                //return successor;
            }
        }
        // 删除的是叶子节点，此时返回的 root 是空
        if(retNode == null){
            return null;
        }
        // 更新当前 retNode height
        retNode.height = 1 + Math.max(getHeight(retNode.left),getHeight(retNode.right));
        // 该节点平衡因子
        int balanceFactor = getBalanceFactor(retNode);
        // 判断是否失衡了
        if(Math.abs(balanceFactor) > 1){
            System.out.println("unbalanced : " + balanceFactor);
        }
        // 平衡维护，以该节点为根的左子树比右子树高，该节点的左子节点(平衡的)也是如此
        // LL (node's left child's left)
        if(balanceFactor > 1 && getBalanceFactor(retNode.left) >= 0){
            return rightRotate(retNode);
        }
        // 平衡维护，以该节点为根的左子树比右子树低，该节点的右子节点(平衡的)也是如此
        // RR (node's right child's right)
        if(balanceFactor < -1 && getBalanceFactor(retNode.right) <= 0){
            return leftRotate(retNode);
        }
        // LR
        if(balanceFactor > 1 && getBalanceFactor(retNode.left) < 0){
            retNode.left = leftRotate(retNode.left);
            return rightRotate(retNode);
        }
        // RL
        if(balanceFactor < -1 && getBalanceFactor(retNode.right) > 0){
            retNode.right = rightRotate(retNode.right);
            return leftRotate(retNode);
        }
        // default
        return retNode;

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

    private Node minimum(Node node){
        if(node.left == null)
            return node;
        return minimum(node.left);
    }

    public boolean contains(K key) {
        return getNode(root,key) != null;
    }

    public V get(K key) {
        Node node = getNode(root,key);
        return node == null ? null : node.value ;
    }

    public void set(K key, V value) {
        Node node = getNode(root, key);
        if(node == null){
            throw new IllegalArgumentException(key + " doesn't exist!");
        }
        node.value = value;

    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }
}
