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

    public void addPlusPlus()
    {
        number++;
    }

    AtomicInteger atomicInteger = new AtomicInteger();

    public void addMyAtomic()
    {
        atomicInteger.getAndIncrement();
    }
}
