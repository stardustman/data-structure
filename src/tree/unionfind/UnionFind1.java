package tree.unionfind;

public class UnionFind1 implements UF {
    // 集合编号
    private int[] id;

    public UnionFind1(int size) {
        this.id = new int[size];
        for (int i = 0; i < id.length; i++){
            // 每个元素分属不同的集合
            id[i] = i;
        }
    }

    /**
     * 查看 p 和 q 是不是在同一个集合
     * @param p
     * @param q
     * @return
     */
    @Override
    public boolean isConnected(int p, int q) {
        return find(p) == find(q);
    }

    /**
     * 合并 p 和 q 分属的两个集合
     * @param p
     * @param q
     * O(n)
     */
    @Override
    public void unionElements(int p, int q) {
        int pID = find(p);
        int qID = find(q);
        if(pID == qID){
            return;
        }
        for (int i = 0; i < id.length; i++) {
            if(id[i] == pID){
                id[i] = qID;
            }
        }
    }

    /**
     * 查找元素 p 对应的集合编号
     * @param p 被查找的元素
     * @return p 所属的集合 id
     * quick find
     * O(1)
     */
    private int find(int p){
        if(p < 0 || p >= id.length){
            throw new IllegalArgumentException("P is out of bound");
        }
        return id[p];
    }

    @Override
    public int getSize() {
        return id.length;
    }
}
