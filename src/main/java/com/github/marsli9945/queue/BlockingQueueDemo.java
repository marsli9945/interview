package com.github.marsli9945.queue;


import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * ArrayBlockingQueue 是一个基于数组结构的有界阻塞队列，此队列按 FIFO（先进先出）原则对元素进行排序
 * LinkedBlockingQueue 是一个基于链表结构的阻塞队列，此队列按 FIFO（先进先出）排序元素，吞吐量通常高于ArrayBlockingQueue
 * SynchronousQueue 是一个不存储元素的阻塞队列。每个插入操作必须等到另一个线程调用移除操作，否则插入操作一直处于阻塞状态，吞吐量通常高于LinkedBlockingQueue
 * <p>
 * 1 队列
 * <p>
 * 2 阻塞队列
 * 2.1 阻塞队列有没有好的一面
 * <p>
 * 2.2 不得不阻塞，你如何管理
 */
public class BlockingQueueDemo
{
    public static void main(String[] args) throws InterruptedException
    {
        ArrayBlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(3);

//        System.out.println(blockingQueue.add("a"));
//        System.out.println(blockingQueue.add("b"));
//        System.out.println(blockingQueue.add("c"));
//        System.out.println(blockingQueue.add("c"));

//        System.out.println(blockingQueue.remove());
//        System.out.println(blockingQueue.remove());
//        System.out.println(blockingQueue.remove());
//        System.out.println(blockingQueue.remove());

        /////////////////////////////////////////////////////////////////////////////

//        System.out.println(blockingQueue.offer("a"));
//        System.out.println(blockingQueue.offer("b"));
//        System.out.println(blockingQueue.offer("c"));
//        System.out.println(blockingQueue.offer("x"));
//
//        System.out.println(blockingQueue.peek());
//
//        System.out.println(blockingQueue.poll());
//        System.out.println(blockingQueue.poll());
//        System.out.println(blockingQueue.poll());
//        System.out.println(blockingQueue.poll());

        /////////////////////////////////////////////////////////////////////////////

//        blockingQueue.put("a");
//        blockingQueue.put("b");
//        blockingQueue.put("c");
//        System.out.println("===========================");
//        blockingQueue.put("x");

//        System.out.println(blockingQueue.take());
//        System.out.println(blockingQueue.take());
//        System.out.println(blockingQueue.take());
//        System.out.println(blockingQueue.take());

        /////////////////////////////////////////////////////////////////////////////

        System.out.println(blockingQueue.offer("a", 2L, TimeUnit.SECONDS));
        System.out.println(blockingQueue.offer("b", 2L, TimeUnit.SECONDS));
        System.out.println(blockingQueue.offer("c", 2L, TimeUnit.SECONDS));
        System.out.println(blockingQueue.offer("x", 2L, TimeUnit.SECONDS));
    }
}
