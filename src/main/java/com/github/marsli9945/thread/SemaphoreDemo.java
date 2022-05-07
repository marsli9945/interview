package com.github.marsli9945.thread;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * 信号量主要用于两个目的
 * 多个共享资源的互斥使用
 * 并发线程数的控制
 */
public class SemaphoreDemo
{
    public static void main(String[] args)
    {
        Semaphore semaphore = new Semaphore(3);//模拟3个停车位

        for (int i = 0; i < 6; i++)//模拟6部汽车
        {
            new Thread(() ->
            {
                try
                {
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName() + "\t 抢到车位");
                    TimeUnit.SECONDS.sleep(3);
                    System.out.println(Thread.currentThread().getName() + "\t 停车3秒后离开车位");
                }
                catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
                finally
                {
                    semaphore.release();
                }
            }, String.valueOf(i)).start();
        }
    }
}
