package com.github.marsli9945.threadPoll;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 第4种获得Java多线程方式，线程池
 */
public class ThreadPollDemo {

    public static void main(String[] args) {
        // Array Arrays
        // Collection Collections
        // Executor Executors

//        List<String> arr = new ArrayList<>();
//        arr.add("a");
//        arr.add("b");
//        arr.add("c");
//        System.out.println(arr);
//
//        List<String> arr2 = Arrays.asList("a","b","c");
//        System.out.println(arr2);

//        ExecutorService pool = Executors.newFixedThreadPool(5);// 一池5个线程
//        ExecutorService pool = Executors.newSingleThreadExecutor();// 一池1个线程
        ExecutorService pool = Executors.newCachedThreadPool();// 一池n个线程

        // 模拟10个用户办理业务，每个用户就是一个来自外部的请求线程
        try {
            for (int i = 0; i < 10; i++) {
                pool.execute(() -> {
                    System.out.println(Thread.currentThread().getName()+"\t 办理业务");
                });
                TimeUnit.MICROSECONDS.sleep(200);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            pool.shutdown();
        }


    }
}
