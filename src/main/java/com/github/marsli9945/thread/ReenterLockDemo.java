package com.github.marsli9945.thread;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Phone implements Runnable
{
    synchronized void sendSMS() throws Exception
    {
        System.out.println(Thread.currentThread().getId() + "\t invoked sendSMS()");
        sendEmail();
    }

    private synchronized void sendEmail() throws Exception
    {
        System.out.println(Thread.currentThread().getId() + "\t invoked sendEmail()");
    }

    //===================================================================================

    Lock lock = new ReentrantLock();

    @Override
    public void run()
    {
        get();
    }

    private void get(){
        lock.lock();
        try
        {
            System.out.println(Thread.currentThread().getName()+"\t invoked get()");
            set();
        } finally
        {
            lock.unlock();
        }
    }

    private void set() {
        lock.lock();
        lock.lock();
        try
        {
            System.out.println(Thread.currentThread().getName()+"\t invoked set()");
        }finally
        {
            lock.unlock();
            lock.unlock();
        }
    }
}


/**
 * 可重入锁
 *
 */
public class ReenterLockDemo
{
    public static void main(String[] args) throws InterruptedException
    {
        Phone phone = new Phone();

        new Thread(()->{
            try
            {
                phone.sendSMS();
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        },"t1").start();

        new Thread(()->{
            try
            {
                phone.sendSMS();
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        },"t2").start();

        TimeUnit.SECONDS.sleep(1);

        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();

        Thread t3 = new Thread(phone,"t3");
        Thread t4 = new Thread(phone,"t4");

        t3.start();
        t4.start();
    }
}
