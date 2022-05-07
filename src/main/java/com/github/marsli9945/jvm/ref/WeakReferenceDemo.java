package com.github.marsli9945.jvm.ref;

import java.lang.ref.WeakReference;

/**
 * 弱引用示例
 */
public class WeakReferenceDemo
{
    public static void main(String[] args)
    {
        Object o1 = new Object();
        WeakReference<Object> weakReference = new WeakReference<>(o1);
        System.out.println("o1: " + o1);
        System.out.println("weakReference: " + weakReference.get());

        o1 = null;
        System.gc();
        System.out.println("=============================");

        System.out.println("o1: " + o1);
        System.out.println("weakReference: " + weakReference.get());
    }
}
