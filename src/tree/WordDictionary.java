package tree;

import java.util.Map;
import java.util.TreeMap;

// https://leetcode-cn.com/problems/design-add-and-search-words-data-structure/?utm_source=LCUS&utm_medium=ip_redirect&utm_campaign=transfer2china
class WordDictionary {
    private class Node {
        // prefix
        boolean isWord;
        // mapping
        Map<Character, Node> next;

        public Node(boolean isWord) {
            this.isWord = isWord;
            next = new TreeMap<>();
        }

        public Node() {
            this.isWord = false;
        }
    }
    Node root;

    /** Initialize your data structure here. */
    public WordDictionary() {
         root = new Node();
    }
    
    public void addWord(String word) {
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
        }
    }
    
    public boolean search(String word) {
        return match(root, word, 0);
    }

    private boolean match(Node node, String word, int index){
        if(index == word.length()){
            return node.isWord;
        }
        char c = word.charAt(index);
        if(c != '.'){
            if(node.next.get(c) == null){
                return false;
            }
            return match(node.next.get(c), word, index + 1);
        }else {
            for (char nextChar: node.next.keySet()) {
                if(match(node.next.get(c), word, index + 1)){
                    return true;
                }
            }
            return false;
        }
    }
}

/**
 * Your WordDictionary object will be instantiated and called as such:
 * WordDictionary obj = new WordDictionary();
 * obj.addWord(word);
 * boolean param_2 = obj.search(word);
 */