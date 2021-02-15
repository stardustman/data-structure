import queue.PriorityQueue;

import java.util.LinkedList;
import java.util.List;
import java.util.TreeMap;

//https://leetcode-cn.com/problems/top-k-frequent-elements/
public class TopKFrequencyElement {
    private class Freq implements Comparable<Freq>{
        public int e, freq;

        public Freq(int e, int freq) {
            this.e = e;
            this.freq = freq;
        }

        @Override
        // 优先级在这定义，频次越低，优先级越高
        public int compareTo(Freq another) {
            if(this.freq < another.freq){
                return 1;
            } else if(this.freq > another.freq){
                return -1;
            }
            else{
                return 0;
            }
        }
    }

    public List<Integer> topKFrequent(int[] nums, int k){
        TreeMap<Integer,Integer> map = new TreeMap<>();
        for (int num : nums) {
            if(map.containsKey(num)){
                map.put(num,map.get(num) + 1);
            } else {
                map.put(num, 1);
            }
        }
        PriorityQueue<Freq> pq = new PriorityQueue<>();
        for (int key: map.keySet()) {
             if(pq.getSize() < k){
                 pq.enqueue(new Freq(key, map.get(key)));
             } else if(map.get(key) > pq.getFront().freq) {
                 pq.dequeue();
                 pq.enqueue(new Freq(key,map.get(key)));
             }
        }
        LinkedList<Integer> res = new LinkedList<>();
        while (!pq.isEmpty()){
            res.add(pq.dequeue().e);
        }
        return res;
    }

}
