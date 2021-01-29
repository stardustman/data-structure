package tree;

public class BSTV1<E extends Comparable<E>> { // E extends Comparable<E> 视为一个 UnknownTypeComparable
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

     public BSTV1(){
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
         // root 特殊处理
         if(root == null){
             root = new Node(e);
             size++;
         } else{
             add(root,e);
         }
     }

     // 向以 node 为根的 bst 中插入元素 E，递归算法
     // 每次都放到叶子节点的位置。
     private void add(Node node, E e){
         if(e.equals(node.e))
             return; //1
         if(node.left == null && e.compareTo(node.e) < 0){
             node.left = new Node(e);
             size++;
             return; //2
         } else if(node.right == null && e.compareTo(node.e) > 0){
             node.right = new Node(e);
             size++;
             return; //3
         }
         if(e.compareTo(node.e) < 0){
             add(node.left,e);
         }
         if(e.compareTo(node.e) > 0){
             add(node.right,e);
         }
     }
}
