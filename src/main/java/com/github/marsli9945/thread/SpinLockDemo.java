package com.github.marsli9945.thread;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

/**
 * 题目：实现一个自旋锁
 * 自旋锁好处：循环比较获取知道成功为止，没有类似wait的阻塞
 */
public class SpinLockDemo
{
    // 原子引用线程
    AtomicReference<Thread> atomicReference = new AtomicReference<>();

    public void myLock()
    {
        Thread thread = Thread.currentThread();
        System.out.println(Thread.currentThread().getName() + "\t come in 😊");
        while (!atomicReference.compareAndSet(null, thread))
        {

        }
    }

    public void myUnlock()
    {
        Thread thread = Thread.currentThread();
        atomicReference.compareAndSet(thread, null);
        System.out.println(Thread.currentThread().getName() + "\t invoked myUnLock()");
    }

    public static void main(String[] args)
    {
        SpinLockDemo spinLockDemo = new SpinLockDemo();

        new Thread(() ->
        {
            spinLockDemo.myLock();
            try
            {
                TimeUnit.SECONDS.sleep(5);
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
            spinLockDemo.myUnlock();
        }, "AA").start();

        try
        {
            TimeUnit.SECONDS.sleep(1);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }

        new Thread(() ->
        {
            spinLockDemo.myLock();
            try
            {
                TimeUnit.SECONDS.sleep(1);
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
            spinLockDemo.myUnlock();
        }, "BB").start();

    }
}
