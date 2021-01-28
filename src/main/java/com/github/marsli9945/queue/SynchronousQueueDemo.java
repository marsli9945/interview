package com.github.marsli9945.queue;

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

public class SynchronousQueueDemo
{
    public static void main(String[] args)
    {
        SynchronousQueue<Object> queue = new SynchronousQueue<>();

        new Thread(() ->
        {
            try
            {
                System.out.println(Thread.currentThread().getName() + "\t put 1");
                queue.put("1");
                System.out.println(Thread.currentThread().getName() + "\t put 2");
                queue.put("2");
                System.out.println(Thread.currentThread().getName() + "\t put 3");
                queue.put("3");
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }, "AAA").start();

        new Thread(() ->
        {
            try
            {
                TimeUnit.SECONDS.sleep(5);
                System.out.println(Thread.currentThread().getName() + "\t" + queue.take());
                TimeUnit.SECONDS.sleep(5);
                System.out.println(Thread.currentThread().getName() + "\t" + queue.take());
                TimeUnit.SECONDS.sleep(5);
                System.out.println(Thread.currentThread().getName() + "\t" + queue.take());
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }, "BBB").start();
    }
}
