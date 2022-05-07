package com.github.marsli9945.jvm.gc;

public class HelloGc
{
    public static void main(String[] args) throws InterruptedException
    {
//        System.out.println("********HelloGc");
//        Thread.sleep(Integer.MAX_VALUE);

//        long totalMemory = Runtime.getRuntime().totalMemory();//返回 java虚拟机 中的内存总量
//        long maxNemory = Runtime.getRuntime().maxMemory();//返回 JAVA虚拟机 试图使用的最大内存
//        System.out.println("TOTAL_MEMORY(-Xms) = "+ totalMemory + "字节、"+(totalMemory / 1024 /1024) + "MB");
//        System.out.println("MAX_MEMORY(-Xmx) = "+ maxNemory + "字节、"+(maxNemory / 1024 /1024) + "MB");

        // 撑爆内存，模拟oom
        byte[] byteArray = new byte[50 * 1024 * 1024];

    }
}
