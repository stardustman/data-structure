package tree.unionfind;

/**
 * Union Find
 * 连通性判断
 */
public interface UF {
    // 判断两个元素是否相连，给元素编号。看看两个元素是不是在同一个集合
    boolean isConnected(int p, int q);
    // 合并两个元素
    void unionElements(int p, int q);
    int getSize();
}
