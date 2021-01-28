package com.github.marsli9945.thread;

import java.util.concurrent.locks.ReentrantLock;

public class T1
{
    volatile int n = 0;

    public void add()
    {
        n++;
    }

    public static void main(String[] args)
    {
        ReentrantLock lock = new ReentrantLock(true);
    }
}
