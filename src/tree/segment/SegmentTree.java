package tree;

/**
 * 线段树视为满二叉树
 * @param <E>
 */
public class SegmentTree<E> {
    private E[] data;
    private E[] tree;
    private Merger<E> merger;
    public SegmentTree(E[] arr, Merger<E> merger){
        this.merger = merger;
        data = (E[])new Object[arr.length];
        for (int i = 0; i < arr.length; i++) {
            data[i] = arr[i];
        }
        tree = (E[])new Object[4 * arr.length];
        buildSegmentTree(0, 0, data.length - 1);
    }

    // 以 treeIndex 为根构建区间 [l...r] 表示的 SegmentTree
    private void buildSegmentTree(int treeIndex, int l, int r){
        if(l == r){
            tree[treeIndex] = data[l];
            return;
        }
        int leftTreeIndex = leftChild(treeIndex);
        int rightTreeIndex = rightChild(treeIndex);
        int mid =  l + (r - l) / 2; // 防止整数溢出
        buildSegmentTree(leftTreeIndex, l, mid);
        buildSegmentTree(rightTreeIndex, mid + 1, r);
        tree[treeIndex] = merger.merge(tree[leftTreeIndex], tree[rightTreeIndex]);

    }

    public E get(int index){
        if(index <0 || index >= data.length){
            throw new IllegalArgumentException("Index is illegal.");
        }
        return data[index];
    }

    public int getSize(){
        return data.length;
    }

    private int leftChild(int index){
        return  2 * index + 1;
    }

    private int rightChild(int index){
        return 2 * index + 2;
    }

    // [queryL...queryR] 区间的值
    public E query(int queryL, int queryR){
        if(queryL < 0 || queryL >= data.length || queryR >= data.length || queryL > queryR){
            throw new IllegalArgumentException("Index is illegal");
        }

        return query(0,0, data.length -1, queryL, queryR);
    }


    /**
     * 在以 treeIndex 为根的线段树中 [l...r],
     * 搜索区间 [queryL...queryR]
     * @param treeIndex segmentTree root index
     * @param l segmentTree leftmost index
     * @param r segmentTree rightmost index
     * @param queryL
     * @param queryR
     * @return
     */
    private E query(int treeIndex, int l, int r, int queryL, int queryR){
        // 找到目标区间了
        if(l == queryL && r == queryR){
            return tree[treeIndex];
        }
        // 中间节点
        int mid = l + (r - l) / 2;
        int leftTreeIndex = leftChild(treeIndex);
        int rightTreeIndex = rightChild(treeIndex);
        // 都在右子树
        if(queryL >= mid + 1){
            return query(rightTreeIndex, mid + 1, r, queryL,queryR);
        }
        // 都在左子树
        else if(queryR <= mid){
            return query(leftTreeIndex, l, mid, queryL, queryR);
        }
        // 跨跃左右子树
        E leftResult = query(leftTreeIndex, l, mid, queryL, mid);
        E rightResult = query(rightTreeIndex, mid + 1, r, mid + 1, queryR);
        // 合并区间
        return  merger.merge(leftResult, rightResult);
    }

    /**
     *
     * @param index
     * @param e
     */
    public void set(int index, E e){
        if(index < 0 || index >= data.length){
            throw new IllegalArgumentException("Index is illegal");
        }
        data[index] = e;
        set(0, 0, data.length, index, e);

    }

    /**
     *
     * @param treeIndex segmentTree root index
     * @param l leftmost index
     * @param r rightmost index
     * @param index 更新 segmentTree index 索引出的值为 e
     * @param e
     */
    private void set(int treeIndex, int l, int r, int index, E e){
        if(l == r){
            tree[treeIndex] = e;
            // 牵连
            return;
        }
        int mid = l + (r - l) / 2;
        int leftTreeIndex = leftChild(treeIndex);
        int rightTreeIndex = rightChild(treeIndex);

        if(index >= mid + 1){
            set(rightTreeIndex, mid + 1, r, index, e);
        }
        else {
            set(leftTreeIndex, l, mid, index, e);
        }
        tree[treeIndex] = merger.merge(tree[leftTreeIndex],tree[rightTreeIndex]);
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append("tree[");
        res.append(tree.length - 1);
        res.append("]: ");
        res.append("[");
        for (int i = 0; i < tree.length; i++) {
            if(tree[i] != null){
                res.append(tree[i]);
            } else {
                res.append("null");
            }
            if(i != tree.length - 1){
                res.append(",");
            }
        }
        res.append("]");
        return res.toString();
    }
}
