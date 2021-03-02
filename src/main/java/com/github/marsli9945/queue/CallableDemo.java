package com.github.marsli9945.queue;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

class MyThread implements Runnable
{

    @Override
    public void run() {

    }
}

class MyThread2 implements Callable<Integer>
{

    @Override
    public Integer call() throws Exception {
        System.out.println(Thread.currentThread().getName()+"**********************come in Callable");
        try {
            TimeUnit.SECONDS.sleep(2);
        }catch (Exception e){
            e.printStackTrace();
        }
        return 1024;
    }
}
/**
 * 第一种，继承Thread类
 * 第二种，实现Runnable接口
 * 多线程中，第3种获取多线程的方式
 */
public class CallableDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //两个线程，一个main主线程，一个是AAA futureTask

        FutureTask<Integer> futureTask = new FutureTask<>(new MyThread2());
        FutureTask<Integer> futureTask2 = new FutureTask<>(new MyThread2());

        new Thread(futureTask, "AAA").start();
        new Thread(futureTask2, "BBB").start();

        int result01 = 100;

        while (!futureTask.isDone()){

        }

        //建议放在最后，要求获得Callable线程的计算结果，如果没有计算完成就要强求，直到计算完成
        //多个线程枪一个futureTask，只计算一次
        int result02 = futureTask.get();
        System.out.println("***************result: "+ (result01 + result02));

    }
}
