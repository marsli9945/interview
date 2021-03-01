package com.github.marsli9945.thread;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * 集合类不安全问题
 * ArrayList
 */
public class ContainerNoSafeDemo
{
    public static void main(String[] args)
    {
        Map<String, String> map = new ConcurrentHashMap<>();//new HashMap<>();
        for (int i = 0; i < 30; i++)
        {
            new Thread(() ->
            {
                map.put(Thread.currentThread().getName(), UUID.randomUUID().toString().substring(0, 8));
                System.out.println(map);
            }, String.valueOf(i)).start();
        }
    }

    public void setNotsafe()
    {
        Set<String> set = new CopyOnWriteArraySet<>();//new HashSet<>();

        for (int i = 0; i < 30; i++)
        {
            new Thread(() ->
            {
                set.add(UUID.randomUUID().toString().substring(0, 8));
                System.out.println(set);
            }, String.valueOf(i)).start();
        }

        /*
         * HashSet底层是HashMap，add()时调用HashMap的put方法，key是add的值，value是Object类
         * CopyOnWriteArraySet底层是CopyOnWriteArrayList
         *
         * 3 解决方案
         *  3.1 new Vector<>() 加了锁，性能下降
         *  3.2 Collections.synchronizedList(new ArrayList<>()) 构建同步的ArrayList
         *  3.3 new CopyOnWriteArrayList<>()
         */
    }


    public void listNotsafe()
    {
//        List<String> list = Arrays.asList("a", "b", "c");
//        list.forEach(System.out::println);

        List<String> list = new CopyOnWriteArrayList<>();

        for (int i = 0; i < 30; i++)
        {
            new Thread(() ->
            {
                list.add(UUID.randomUUID().toString().substring(0, 8));
                System.out.println(list);
            }, String.valueOf(i)).start();
        }
        //java.util.ConcurrentModificationException并发修改异常

        /*
         * 1 故障现象
         *      java.util.ConcurrentModificationException并发修改异常
         *
         * 2 导致原因
         *  并发争抢修改，导致数据数据不一致异常，并发修改异常。
         *
         * 3 解决方案
         *  3.1 new Vector<>() 加了锁，性能下降
         *  3.2 Collections.synchronizedList(new ArrayList<>()) 构建同步的ArrayList
         *  3.3 new CopyOnWriteArrayList<>()
         *
         * 4 优化建议(同样的错误不犯第2次)
         *  new CopyOnWriteArrayList<>()
         *  写时复制，读写分离
         *  提高并发能力
         */
    }
}
