package com.github.marsli9945.thread;

import java.util.concurrent.atomic.AtomicInteger;

class MyData
{
    volatile int number = 0;

    void addTo60()
    {
        number = 60;
        System.out.println("num add 60");
    }

    void addPlusPlus()
    {
        number++;
    }

    AtomicInteger atomicInteger = new AtomicInteger();

    void addMyAtomic()
    {
        atomicInteger.getAndIncrement();
    }
}
