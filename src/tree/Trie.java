package tree;
import java.util.Map;
import java.util.TreeMap;
// 多叉树，字典树，前缀树，字符串集合
// O(str.length)
public class Trie {
    private class Node{
        // prefix
        boolean isWord;
        // mapping
        Map<Character, Node> next;

        public Node(boolean isWord){
            this.isWord = isWord;
            next = new TreeMap<>();
        }

        public Node(){
            this.isWord = false;
        }
    }

    private Node root;
    private int size;

    public Trie(){
        root = new Node();
        size = 0;
    }

    public int getSize(){
        return size;
    }

    // 向 trie 中添加一个新的单词 word
    public void add(String word){
        Node cur = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if(cur.next.get(c) == null){
                cur.next.put(c, new Node());
            }
            cur = cur.next.get(c);
        }
        // 确实是一个新的 word
        if(!cur.isWord){
            cur.isWord = true;
            size++;
        }

    }
    // 查询 word 是否在 trie 中
    public boolean contains(String word){
        Node cur = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if(cur.next.get(c) == null){
                return false;
            }
            cur = cur.next.get(c);
        }
        // pan vs panda 前缀
        return cur.isWord;
    }

    // 查询 trie 中是否有以 prefix 为前缀的单词
    public boolean isPrefix(String prefix){
        Node cur = root;
        for (int i = 0; i < prefix.length(); i++) {
            char c = prefix.charAt(i);
            if(cur.next.get(c) == null){
                return false;
            }
            cur = cur.next.get(c);
        }
        return true;
    }


}
