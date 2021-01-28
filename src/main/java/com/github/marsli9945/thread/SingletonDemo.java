package com.github.marsli9945.thread;

public class SingletonDemo
{
    // 禁止指令重排，防止instance未实际初始化时被外部获取
    private static volatile SingletonDemo instance = null;

    private SingletonDemo()
    {
        System.out.println(Thread.currentThread().getName() + "\t 我是构造方法SingletonDemo()");
    }

    /**
     * DCL(Double check lock 双端检锁机制)单例模式
     */
    public static SingletonDemo getInstance()
    {
        if (instance == null)
        {
            synchronized (SingletonDemo.class)
            {
                /*
                 * 只判断内存是否被分配
                 * 指令重排后可能会出现，内存被分配出去但还未初始化完成
                 */
                if (instance == null)
                {
                    instance = new SingletonDemo();
                }
            }
        }
        return instance;
    }

    public static void main(String[] args)
    {
        // 单线程(main线程的操作动作......)
//        System.out.println(SingletonDemo.getInstance() == SingletonDemo.getInstance());
//        System.out.println(SingletonDemo.getInstance() == SingletonDemo.getInstance());
//        System.out.println(SingletonDemo.getInstance() == SingletonDemo.getInstance());
//
//        System.out.println();
//        System.out.println();
//        System.out.println();

        //并发多线程后，情况发生变化
        for (int i = 0; i < 10; i++)
        {
            new Thread(() ->
            {
                SingletonDemo.getInstance();
            }, String.valueOf(i)).start();
        }
    }
}
