package hash;

import java.util.TreeMap;

/**
 * 使用 separate chaining
 * 其实就是利用 hash(key) 进行分组
 * 分层、分组、有序 提升效率三大指导思想
 * 动态扩容、缩容
 * @param <K>
 * @param <V>
 */
public class HashTable1<K, V> {
    // TreeMap 数组
    private TreeMap<K, V>[] hashtable;
    private int M;
    int size;

    private static final int upperTol = 10;
    private static final int lowerTol = 2;
    private static final int initCapacity = 7;

    public HashTable1(int M ) {

        this.M = M;
        this.size = 0;
        hashtable = new TreeMap[M];
        for (int i = 0; i < M; i++) {
            hashtable[i] = new TreeMap<>();
        }
    }

    public HashTable1(){
        this(initCapacity);
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
            if(size >= upperTol * M){ // size/M >= upperTol
                resize(2 * M);
            }
        }
    }

    public V remove(K key){
        TreeMap<K, V> map = hashtable[hash(key)];
        V ret = null;
        if(map.containsKey(key)){
            ret = map.remove(key);
            size--;

            if(size < lowerTol * M && M / 2 >= initCapacity){
                resize(M / 2);
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
