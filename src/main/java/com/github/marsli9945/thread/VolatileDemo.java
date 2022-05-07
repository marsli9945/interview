package com.github.marsli9945.thread;

import java.util.concurrent.TimeUnit;

/**
 * 1.验证volatile的可见性
 * 1.1 假如int number = 0;前无volatile关键字修饰,没有可见性
 * 1.2 添加了volatile，可以解决可见性问题
 * <p>
 * 2.验证volatile不保证原子性
 * 2.1 原子性是什么意思？
 * 完整性，不可分割
 * 某个线程正在做莫哥具体业务时，中间不可以被加塞或分割
 * 需要整体完整，要么同事成功，要么同事失败
 * 2.2 是否可以保证原子性？
 * <p>
 * 2.3 why
 * <p>
 * 2.4 如何解决原子性
 * 加sync
 * 使用juc下的AtomicInteger
 */
public class VolatileDemo
{
    public static void main(String[] args)
    {
        MyData myData = new MyData();

        for (int i = 0; i < 20; i++)
        {
            new Thread(() ->
            {
                for (int j = 0; j < 1000; j++)
                {
                    myData.addPlusPlus();
                    myData.addMyAtomic();
                }
            }, String.valueOf(i)).start();
        }

        while (Thread.activeCount() > 2)
        {
            Thread.yield();
        }

        System.out.println(Thread.currentThread().getName() + "\t finally number value: " + myData.number);
        System.out.println(Thread.currentThread().getName() + "\t finally atomicInteger value: " + myData.atomicInteger);
    }

    /**
     * volatile可以保证可见性，及时通知其他线程，主物理内存的值已经被修改
     */
    public static void seeOkByVolatile()
    {
        MyData myData = new MyData();
        System.out.println("start.....");

        new Thread(() ->
        {
            System.out.println(Thread.currentThread().getName() + " come in");
            try
            {
                TimeUnit.SECONDS.sleep(3);
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
            myData.addTo60();
        }, "AAA").start();

        while (myData.number == 0)
        {
        }

        System.out.println(Thread.currentThread().getName() + " mission is over");
    }
}
