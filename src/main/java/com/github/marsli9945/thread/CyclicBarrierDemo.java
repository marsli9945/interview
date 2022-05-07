package com.github.marsli9945.thread;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * 加法等待
 * 当等待线程到达指定数时出发回调方法
 */
public class CyclicBarrierDemo
{
    public static void main(String[] args)
    {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(7, () ->
                System.out.println("***召唤神龙"));

        for (int i = 0; i < 7; i++)
        {
            final int tempInt = i;
            new Thread(() ->
            {
                System.out.println(Thread.currentThread().getName() + "\t 收集到第:" + tempInt + "龙珠");
                try
                {
                    cyclicBarrier.await();
                }
                catch (InterruptedException | BrokenBarrierException e)
                {
                    e.printStackTrace();
                }
            }, String.valueOf(i)).start();
        }
    }
}
