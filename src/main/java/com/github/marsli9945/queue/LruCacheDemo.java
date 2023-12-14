package com.github.marsli9945.queue;

import java.util.LinkedHashMap;
import java.util.Map;

public class LruCacheDemo {

    private int capacity;//缓存容量

    public static void main(String[] args) {
        try
            {
                LruCacheDemo lruCacheDemo = new LruCacheDemo();
                lruCacheDemo.capacity = 100;
//                lruCacheDemo.test();
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        new Thread(() -> {
            System.out.println();
        });
    }
}
