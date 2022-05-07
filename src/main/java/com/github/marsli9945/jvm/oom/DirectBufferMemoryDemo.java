package com.github.marsli9945.jvm.oom;

import java.nio.ByteBuffer;

/**
 * -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:MaxDirectMemorySize=5m
 */
public class DirectBufferMemoryDemo
{
    public static void main(String[] args) throws InterruptedException
    {
        System.out.println("配置的maxDirectMemory: " + (sun.misc.VM.maxDirectMemory() / (double)1024 / 1024) + "MB");
        Thread.sleep(3000);

        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(6 * 1024 * 1024);
        // Exception in thread "main" java.lang.OutOfMemoryError: Direct buffer memory
    }
}
