package com.github.marsli9945.queue;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class ShareData
{
    private int num = 0;
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    int getNum()
    {
        return num;
    }

    void increment()
    {
        lock.lock();
        try
        {
            //1 判断
            while (num != 0)
            {
                //等待，不能生产
                condition.await();
            }
            //2 干活
            num++;
            System.out.println(Thread.currentThread().getName() + "\t" + num);
            //3 通知唤醒
            condition.signalAll();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            lock.unlock();
        }
    }

    void decrement()
    {
        lock.lock();
        try
        {
            //1 判断
            while (num == 0)
            {
                //等待，不能消费
                condition.await();
            }
            //2 干活
            num--;
            System.out.println(Thread.currentThread().getName() + "\t" + num);
            //3 通知唤醒
            condition.signalAll();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            lock.unlock();
        }
    }
}

/**
 * 传统版生产消费模式
 * 题目：初始值为0的变量，两个线程对其交叉操作，一个加1一个减1，来5轮
 * <p>
 * 1 线程     操作（方法）      资源类
 * 2 判断     干活              通知
 * 3 防止虚假唤醒机制   -> 多线程的判断必须用while，if会虚假唤醒跳过判断
 */
public class ProdConsumer_TraditionDemo
{
    public static void main(String[] args)
    {
        ShareData shareData = new ShareData();

        new Thread(() ->
        {
            for (int i = 0; i < 5; i++)
            {
                shareData.increment();
            }
        }, "p1").start();

        new Thread(() ->
        {
            for (int i = 0; i < 5; i++)
            {
                shareData.decrement();
            }
        }, "c1").start();

        new Thread(() ->
        {
            for (int i = 0; i < 5; i++)
            {
                shareData.increment();
            }
        }, "p2").start();

        new Thread(() ->
        {
            for (int i = 0; i < 5; i++)
            {
                shareData.decrement();
            }
        }, "c2").start();
    }
}
