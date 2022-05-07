package com.github.marsli9945.jvm.ref;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

/**
 * 虚引用示例
 */
public class PhantomReferenceDemo
{
    public static void main(String[] args) throws InterruptedException
    {
        Object o1 = new Object();
        final ReferenceQueue<Object> queue = new ReferenceQueue<>();
        PhantomReference<Object> phantomReference = new PhantomReference<>(o1, queue);

        System.out.println("o1: "+o1);
        System.out.println("weakReference: "+phantomReference.get());
        System.out.println("queue: "+queue.poll());

        System.out.println("=================");

        o1 = null;
        System.gc();
        Thread.sleep(500);

        System.out.println("o1: "+o1);
        System.out.println("weakReference: "+phantomReference.get());
        System.out.println("queue: "+queue.poll());
    }
}
