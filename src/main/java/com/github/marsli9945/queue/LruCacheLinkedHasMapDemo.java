package com.github.marsli9945.queue;

import java.util.LinkedHashMap;
import java.util.Map;

public class LruCacheLinkedHasMapDemo<K,V> extends LinkedHashMap<K,V> {

    private int capacity;//缓存容量

    public LruCacheLinkedHasMapDemo(int capacity){
        super(capacity, 0.75F,true);
        this.capacity = capacity;
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        return super.size() > capacity;
    }

    public static void main(String[] args) {
        final LruCacheLinkedHasMapDemo<Integer, String> lruCacheLinkedHasMapDemo = new LruCacheLinkedHasMapDemo<>(3);

        lruCacheLinkedHasMapDemo.put(1, "a");
        lruCacheLinkedHasMapDemo.put(2, "b");
        lruCacheLinkedHasMapDemo.put(3, "c");
        System.out.println(lruCacheLinkedHasMapDemo.keySet());

        lruCacheLinkedHasMapDemo.put(4, "d");
        System.out.println(lruCacheLinkedHasMapDemo.keySet());

        lruCacheLinkedHasMapDemo.put(3, "c");
        System.out.println(lruCacheLinkedHasMapDemo.keySet());
        lruCacheLinkedHasMapDemo.put(3, "c");
        System.out.println(lruCacheLinkedHasMapDemo.keySet());
        lruCacheLinkedHasMapDemo.put(3, "c");
        System.out.println(lruCacheLinkedHasMapDemo.keySet());
        lruCacheLinkedHasMapDemo.put(5, "e");
        System.out.println(lruCacheLinkedHasMapDemo.keySet());
    }
}
