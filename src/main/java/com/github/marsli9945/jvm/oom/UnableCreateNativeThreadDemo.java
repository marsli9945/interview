package com.github.marsli9945.jvm.oom;

public class UnableCreateNativeThreadDemo
{
    public static void main(String[] args)
    {
//        Thread thread = new Thread();
//        thread.start();
//        thread.start();
        // Exception in thread "main" java.lang.IllegalThreadStateException

        for (int i = 0; ; i++)
        {
            System.out.println("*************i="+i);

            new Thread(()->{
                try{Thread.sleep(Integer.MAX_VALUE);}catch (InterruptedException e) {e.printStackTrace();}
            },""+i).start();
            // Exception in thread "main" java.lang.OutOfMemoryError: unable to create new native thread
        }
    }
}
