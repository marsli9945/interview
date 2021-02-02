package com.github.marsli9945.queue;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class ShareData{
    private int num = 0;
    private Lock lock = new ReentrantLock();

    int getNum(){
        return  num;
    }

    void intraction(){
        lock.lock();
        num++;
        System.out.println("生产");
        lock.unlock();
    }

    void subtraction(){
        lock.lock();
        num--;
        System.out.println("消费");
        lock.unlock();
    }
}

/**
 * 传统版生产消费模式
 * 题目：初始值为0的变量，两个线程对其交叉操作，一个加1一个减1，来5轮
 */
public class ProdConsumer_TraditionDemo
{
    public static void main(String[] args) throws InterruptedException
    {
        ShareData shareData = new ShareData();

        new Thread(()->{
            for (int i = 0; i < 5; i++)
            {
                shareData.intraction();
            }
        },"aa").start();

        new Thread(()->{
            for (int i = 0; i < 5; i++)
            {
                shareData.subtraction();
            }
        },"bb").start();

        TimeUnit.SECONDS.sleep(3);

        System.out.println(shareData.getNum());
    }
}
