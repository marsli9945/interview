package com.github.marsli9945.thread;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantReadWriteLock;

class MyCache
{
    private volatile Map<String, Object> map = new HashMap<>();
    private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    public void put(String key, Object value) {
        lock.writeLock().lock();

        try
        {
            System.out.println(Thread.currentThread().getName()+"\t 正在写入: "+ key);
            map.put(key,value);
            System.out.println(Thread.currentThread().getName()+"\t 写入完成: "+ key);

        } finally
        {
            lock.writeLock().unlock();
        }

    }

    public void get(String key) {
        lock.readLock().lock();
        try
        {
            System.out.println(Thread.currentThread().getName()+"\t 正在读取: "+ key);
            Object result = map.get(key);
            System.out.println(Thread.currentThread().getName()+"\t 读取完成: "+ result);
        } finally
        {
            lock.readLock().unlock();
        }

    }
}

/**
 * 读写锁
 */
public class ReadWriteLockDemo
{

    public static void main(String[] args)
    {
        MyCache myCache = new MyCache();

        for (int i = 0; i < 5; i++)
        {
            final int tempInt = i;
            new Thread(()->{
                myCache.put(tempInt+"", tempInt+"");
            },String.valueOf(i)).start();
        }

        for (int i = 0; i < 5; i++)
        {
            final int tempInt = i;
            new Thread(()->{
                myCache.get(tempInt+"");
            },String.valueOf(i)).start();
        }
    }
}
