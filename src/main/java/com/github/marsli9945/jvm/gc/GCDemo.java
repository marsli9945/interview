package com.github.marsli9945.jvm.gc;

import java.util.Random;

/**
 * 1 新生代和老年代都用串行(de)
 * -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:+PrintCommandLineFlags -XX:+UseSerialGC
 *
 * 2 新生代用并行，老年代用串行(ParNew+Tenured)
 * -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:+PrintCommandLineFlags -XX:+UseParNewGC
 *
 * 备注情况
 * OpenJDK 64-Bit Server VM warning:
 * Using the ParNew young collector with the Serial old collector is deprecated
 * and will likely be removed in a future release
 *
 * 3 新生代和老年代都是并行，什么都不配默认也是这个收集器(PSYoungGen+ParOldGen)
 * -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:+PrintCommandLineFlags -XX:+UseParallelGC
 * -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:+PrintCommandLineFlags
 *
 * 4 新生代和老年代都是并行,效果与3一致(PSYoungGen+ParOldGen)
 * -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:+PrintCommandLineFlags -XX:+UseParallelNewGC
 *
 * 5 新生代用并行，老年代用并发的CMS垃圾收集器(par new generation + concurrent mark-sweep generation)
 * -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:+PrintCommandLineFlags -XX:+UseConcMarkSweepGC
 *
 * 6 G1 后面专门讲
 * -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:+PrintCommandLineFlags -XX:+UseG1GC
 *
 * 7 老年代串行，理论知道即可，实际java8中已经被优化了
 * -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:+PrintCommandLineFlags -XX:+UseSerialOldGC
 */
public class GCDemo
{
    public static void main(String[] args)
    {
        System.out.println("******************GCDemo hello");
        try
        {
            String str = "atguigu";
        while (true) {
            str += str + new Random().nextInt(1111111) + new Random().nextInt(22222222);
            str.intern();
        }
        }catch (Throwable e) {
            e.printStackTrace();
        }
    }
}
