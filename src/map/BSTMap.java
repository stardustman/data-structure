package map;

public class BSTMap<K extends Comparable<K>,V> implements Map<K,V> {
    private class Node{
        public K key;
        public V value;
        public Node left, right;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            this.left = null;
            this.right = null;
        }
    }

    private Node root;
    private int size;

    public BSTMap() {
        this.root = null;
        this.size = 0;
    }

    @Override
    public void add(K key, V value) {
        root = add(root,key,value);
    }

    private Node add(Node node, K key, V value){
        if(node == null){
            size++;
            return new Node(key,value);
        }
        if(key.compareTo(node.key) < 0){
            node.left = add(node.left,key,value);
        }
        else if(key.compareTo(node.key) > 0){
            node.right = add(node.right,key,value);
        } else{ // key.compareTo(node.key) ==  0
            node.value = value;
        }
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

    @Override
    public V remove(K key) {
        Node node = getNode(root,key);
        if(node != null){
            remove(root, key);
            return node.value;
        }
        return null;
    }

    // 删除以 node 为 bst root 中键位 key 的节点。
    // 返回删除节点后新的 bst 的根
    private Node remove(Node node, K key){
        if(node == null){
            return null;
        }
        if(key.compareTo(node.key) < 0){
            node.left = remove(node.left, key);
            return node;
        } else if(key.compareTo(node.key) > 0){
            node.right = remove(node.right, key);
            return node;
        } else{
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

        }
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

    @Override
    public boolean contains(K key) {
        return getNode(root,key) != null;
    }

    @Override
    public V get(K key) {
        Node node = getNode(root,key);
        return node == null ? null : node.value ;
    }

    @Override
    public void set(K key, V value) {
        Node node = getNode(root, key);
        if(node == null){
            throw new IllegalArgumentException(key + " doesn't exist!");
        }
        node.value = value;

    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }
}
