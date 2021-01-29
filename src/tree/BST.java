package tree;

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
