package com.github.marsli9945.queue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

class MyResource
{
    private volatile boolean FLAG = true; //默认开启，进行生产+消费
    private AtomicInteger atomicInteger = new AtomicInteger();

    private BlockingQueue<String> blockingQueue;

    MyResource(BlockingQueue<String> blockingQueue)
    {
        this.blockingQueue = blockingQueue;
        System.out.println(blockingQueue.getClass().getName());
    }

    void myProd() throws Exception
    {
        String data;
        boolean retValue;
        while (FLAG)
        {
            data = atomicInteger.incrementAndGet() + "";
            retValue = blockingQueue.offer(data, 2L, TimeUnit.SECONDS);

            if (retValue)
            {
                System.out.println(Thread.currentThread().getName() + "\t 插入队列" + data + "成功");
            }
            else
            {
                System.out.println(Thread.currentThread().getName() + "\t 插入队列" + data + "失败");
            }
            TimeUnit.SECONDS.sleep(1);
        }
        System.out.println(Thread.currentThread().getName() + "\t 大老板叫停，表示FLAG=false，生产动作结束 ");
    }

    void myConsumer() throws Exception
    {
        String result;
        while (FLAG)
        {
            result = blockingQueue.poll(2L, TimeUnit.SECONDS);
            if (null == result || result.equalsIgnoreCase(""))
            {
                FLAG = false;
                System.out.println(Thread.currentThread().getName() + "\t 超过2秒没有取到蛋糕，消费推出");
                System.out.println();
                System.out.println();
                return;
            }

            System.out.println(Thread.currentThread().getName() + "\t 消费队列" + result + "成功");
        }
        System.out.println(Thread.currentThread().getName() + "\t 大老板叫停，表示FLAG=false，生产动作结束 ");
    }

    void stop()
    {
        FLAG = false;
    }

}

/**
 * volatile/CAS/atomicInteger/BlockQueue/线程交互/原子引用
 */
public class ProdConsumer_BlockQueueDemo
{
    public static void main(String[] args) throws Exception
    {
        MyResource myResource = new MyResource(new ArrayBlockingQueue<>(10));

        new Thread(() ->
        {
            System.out.println(Thread.currentThread().getName() + "\t 生产线程启动");

            try
            {
                myResource.myProd();
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }, "p1").start();

        new Thread(() ->
        {
            System.out.println(Thread.currentThread().getName() + "\t 消费线程启动");
            System.out.println();
            System.out.println();

            try
            {
                myResource.myConsumer();
                System.out.println();
                System.out.println();
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }, "c1").start();

        //暂停一会
        try
        {
            TimeUnit.SECONDS.sleep(5);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        System.out.println();
        System.out.println();
        System.out.println();

        System.out.println("5秒时间到，大老板main线程叫停，活动结束");
        myResource.stop();
    }
}
