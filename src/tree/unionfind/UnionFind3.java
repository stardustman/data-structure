package tree.unionfind;

public class UnionFind3 implements UF {
    private int[] parent;
    // 记录每一个节点的子节点数量，基于 size 优化
    // size[i] 表示根节点为 i 的树的节点数量
    private int[] sz;
    public UnionFind3(int size) {
        parent = new int[size];
        sz = new int[size];
        for (int i = 0; i < size; i++) {
            // 每一个节点的父节点都是本身。
            // 都是一个独立的树，森林
            parent[i] = i;
            sz[i] = 1;
        }
    }

    @Override
    public boolean isConnected(int p, int q) {
        return find(p) == find(q);
    }

    @Override
    public void unionElements(int p, int q) {
        int pRoot = find(p);
        int qRoot = find(q);
        if(pRoot == qRoot){
            return;
        }
        if(sz[pRoot] < sz[qRoot]){
            parent[pRoot] = qRoot;
            sz[qRoot] += sz[pRoot];
        } else {
            parent[qRoot] = pRoot;
            sz[pRoot] += sz[qRoot];
        }

    }

    @Override
    public int getSize() {
        return parent.length;
    }

    /**
     * 查找元素 p 所属于的集合编号
     * @param p 被查找的元素
     * @return
     * O(h) 复杂度
     */
    private int find(int p){
        if(p < 0 || p >= parent.length){
            throw new IllegalArgumentException("P is out of bound");
        }
        // 向上查找
        while (p != parent[p]){
            p = parent[p];
        }
        return p;
    }
}
