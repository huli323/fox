package algorithm.leetCode;

import java.util.*;

/**
 Design and implement a data structure for Least Recently Used (LRU) cache. It should support the following operations: get and put.

 get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
 put(key, value) - Set or insert the value if the key is not already present.
 When the cache reached its capacity, it should invalidate the least recently used item before inserting a new item.
 */
public class LRUCache {
    private List<Integer> list;
    private int capacity;
    private Map<Integer, Integer> map;

    public LRUCache(int capacity) {
        list = new ArrayList<>();
        map = new HashMap<>();
        this.capacity = capacity;
    }

    public int get(int key) {
        Integer res = map.get(key);
        System.out.println("return " + (res == null ? -1 : res));
        if(res != null) {
            list.remove(Integer.valueOf(key));
            list.add(key);
            return res;
        }

        return -1;
    }

    public void put(int key, int value) {
        if(map.get(key) != null){
            list.remove(Integer.valueOf(key));
        } else if(list.size() >= capacity){
            Integer tmp = list.remove(0);
            map.remove(Integer.valueOf(tmp));
            System.out.println("remove key " + tmp);
        }
        map.put(key, value);
        list.add(key);
    }

    public static void main(String[] args) {
        LRUCache cache = new LRUCache( 2 /* capacity */ );

        cache.put(2, 1);
        cache.put(2, 2);
        cache.get(2);
        cache.put(1, 1);
        cache.put(4, 1);
        cache.get(2);
    }
}
