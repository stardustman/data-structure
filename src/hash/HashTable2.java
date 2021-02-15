package hash;

import java.util.TreeMap;

/**
 * 使用 separate chaining
 * 其实就是利用 index = hash(key) 进行分组
 * 分层、分组、有序 提升效率三大指导思想
 * 动态扩容、缩容
 * 相较于二叉树牺牲了顺序性
 * @param <K>
 * @param <V>
 */
public class HashTable2<K, V> {
    private final int[] capacity = {
            // 质数
            53, 97, 193, 389, 769, 1543, 3079,
            6151, 12289, 24593, 49157, 98317, 196613, 393241,
            786433, 1572869, 3145739, 6291469, 12582917,
            25165843, 100663319, 201326611, 402653189, 805306457, 1610612741
    };
    // TreeMap 数组
    // 对于 TreeMap 的 K 需要可比较性的
    private TreeMap<K, V>[] hashtable;
    private int M;
    int size;

    private static final int upperTol = 10;
    private static final int lowerTol = 2;
    private int initCapacityIndex = 0;

    public HashTable2( ) {

        this.M = capacity[initCapacityIndex];
        this.size = 0;
        hashtable = new TreeMap[M];
        for (int i = 0; i < M; i++) {
            hashtable[i] = new TreeMap<>();
        }
    }


    private int hash(K key){
        return (key.hashCode() & 0x7fffffff) % M;
    }

    public int getSize(){
        return this.size;
    }

    public void add(K key, V value){
        TreeMap<K, V> map = hashtable[hash(key)];
        if(map.containsKey(key)){
            map.put(key,value);
        } else {
            map.put(key,value);
            size++;
            if(size >= upperTol * M && initCapacityIndex + 1 < capacity.length){ // size/M >= upperTol
                initCapacityIndex++;
                resize(capacity[initCapacityIndex]);
            }
        }
    }

    public V remove(K key){
        TreeMap<K, V> map = hashtable[hash(key)];
        V ret = null;
        if(map.containsKey(key)){
            ret = map.remove(key);
            size--;

            if(size < lowerTol * M && initCapacityIndex -1 >= 0){
                initCapacityIndex--;
                resize(capacity[initCapacityIndex]);
            }
        }
        return ret;
    }

    private void resize(int newM){

        TreeMap<K, V>[] newHashTable = new TreeMap[newM];
        for (int i = 0; i < newM; i++) {
            newHashTable[i] = new TreeMap<>();
        }
        int oldM = M;
        this.M = newM;
        for (int i = 0; i < oldM; i++) {
            TreeMap<K, V> map = hashtable[i];
            for (K key: map.keySet()) {
                newHashTable[hash(key)].put(key, map.get(key));
            }
        }
        this.hashtable = newHashTable;
    }

    public void set(K key, V value){
        TreeMap<K, V> map = hashtable[hash(key)];
        if(!map.containsKey(key)){
            throw new IllegalArgumentException(key + " doesn't exist");
        }
        map.put(key,value);
    }

    public boolean contains(K key){
        return hashtable[hash(key)].containsKey(key);
    }

    public V get(K key){
        return hashtable[hash(key)].get(key);
    }

}
