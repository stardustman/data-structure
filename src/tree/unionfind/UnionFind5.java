package tree.unionfind;

public class UnionFind5 implements UF {
    private int[] parent;
    // rank[i] 表示根节点为 i 的树的<层数>，(排名)，作为合并的参考
    private int[] rank;
    public UnionFind5(int size) {
        parent = new int[size];
        rank = new int[size];
        for (int i = 0; i < size; i++) {
            // 每一个节点的父节点都是本身。
            // 都是一个独立的树，森林
            parent[i] = i;
            rank[i] = 1;
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
        // 根据两个元素所在树的 rank 不同判断合并方向
        // 将 rank 低的集合合并到 rank 高的集合上
        if(rank[pRoot] < rank[qRoot]){
            parent[pRoot] = qRoot;
        } else if(rank[qRoot] < rank[pRoot]){
            parent[qRoot] = pRoot;
        } else {
            parent[qRoot] = pRoot;
            rank[pRoot] += 1;
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
            // 路径压缩,当前节点的父节点设置为，父节点的父节点
            parent[p] = parent[parent[p]];
            p = parent[p];
        }
        return p;
    }
}
