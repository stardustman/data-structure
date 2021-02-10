package heap;

import array.Array;

public class MaxHeap<E extends Comparable<E>> {
    private Array<E> data;
    public MaxHeap(int capacity){
        data = new Array<>(capacity);
    }

    public MaxHeap(E[] arr){
        data = new Array<>(arr);
        // heapify, 直接抛弃一半的元素进行 heapify
        // 从最后一个非叶子节点进行 siftDown
        for (int i = parent(arr.length - 1); i >= 0; i--) {
            siftDown(i);
        }
    }

    public MaxHeap(){
        data = new Array<>();
    }

    public int size(){
        return data.getSize();
    }
    public boolean isEmpty(){
        return data.isEmpty();
    }
    // 返回完全二叉树数组表示中，一个索引所表示节点的父节点的索引
    public int parent(int index){
        if(index == 0){
            throw new IllegalArgumentException("index-0 doesn't have parent");
        }
        return (index - 1) / 2;
    }

    public int leftChild(int index){
        return 2*index + 1;
    }

    public int rightChild(int index){
        return 2*index + 2;
    }

    // 向堆中添加元素
    public void add(E e){
        data.addLast(e);
        siftUp(data.getSize() - 1);
    }

    private void siftUp(int k){
        // root  index = 0
        while (k > 0 && data.get(parent(k)).compareTo(data.get(k)) < 0){
            data.swap(k, parent(k));
            k = parent(k);
        }
    }

    public E findMax(){
        if(data.isEmpty()){
            throw new IllegalArgumentException("Can not findMax when heap is empty");
        }
        return data.get(0);
    }

    public E extractMax(){
        E ret = findMax();
        data.swap(0, data.getSize() - 1);
        data.removeLast();
        siftDown(0);
        return ret;
    }

    private void siftDown(int k){

        // 完全二叉树
        while (leftChild(k) < data.getSize()){
            int j = leftChild(k);
            // 看一下有没有右孩子
            if(j + 1 < data.getSize() && data.get(j + 1).compareTo(data.get(j)) > 0){
                // 右孩子值更大
                j = rightChild(k);
                // j++;
            }

            if(data.get(k).compareTo(data.get(j)) >= 0){
                break;
            }
            data.swap(k, j);
            k = j;
        }
    }

    public E replace(E e){
        E ret = findMax();
        // 堆顶置换为 e
        data.set(0, e);
        siftDown(0);
        return ret;
    }
    // 将任意数组整理成堆的形状
    public void heapify(){

    }
}
