package hash;

import java.util.TreeMap;

/**
 * 使用 separate chaining
 * 其实就是利用 hash(key) 进行分组
 * 分层、分组、有序 提升效率三大指导思想
 * @param <K>
 * @param <V>
 */
public class HashTable0<K, V> {
    // TreeMap 数组
    private TreeMap<K, V>[] hashtable;
    private int M;
    int size;

    public HashTable0(int M ) {

        this.M = M;
        this.size = 0;
        hashtable = new TreeMap[M];
        for (int i = 0; i < M; i++) {
            hashtable[i] = new TreeMap<>();
        }
    }

    public HashTable0(){
        this(97);
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
        }
    }

    public V remove(K key){
        TreeMap<K, V> map = hashtable[hash(key)];
        V ret = null;
        if(map.containsKey(key)){
            ret = map.remove(key);
            size--;
        }
        return ret;
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
