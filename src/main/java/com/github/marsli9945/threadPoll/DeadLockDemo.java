package com.github.marsli9945.threadPoll;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

class HoldLockThread implements Runnable
{

    private String lockA;
    private String lockB;

    public HoldLockThread(String lockA, String lockB)
    {
        this.lockA = lockA;
        this.lockB = lockB;
    }

    @Override
    public void run()
    {
        synchronized (lockA)
        {
            System.out.println(Thread.currentThread().getName() + "\t 自己持有：" + lockA + "尝试获得：" + lockB);

            try
            {
                TimeUnit.SECONDS.sleep(2);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }

            synchronized (lockB)
            {
                System.out.println(Thread.currentThread().getName() + "\t 自己持有：" + lockB + "尝试获得：" + lockA);
            }
        }

    }
}

/**
 * 死锁是指两个或两个以上线程在执行过程中
 * 因争夺资源而造成相互等待的现象
 * 若无外力干涉他们将无法推进下去
 */
public class DeadLockDemo
{

    public static void main(String[] args)
    {

        String lockA = "lockA";
        String lockB = "lockB";

        new Thread(new HoldLockThread(lockA, lockB), "ThreadAAA").start();
        new Thread(new HoldLockThread(lockB, lockA), "ThreadBBB").start();

    }
    /**
     * 死锁的排查
     * 用到的命令：jps、jstack
     * 1、jps -l
     * 2、jstack <前一步找到的进程号>
     *
     */
}
