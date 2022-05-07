package com.github.marsli9945.jvm.ref;

import java.util.HashMap;
import java.util.WeakHashMap;

public class WeakHasMapDemo
{
    public static void main(String[] args)
    {
        myHasMap();
        System.out.println("==========================");
        myWeakHasMap();
    }

    private static void myHasMap()
    {
        HashMap<Integer, String> map = new HashMap<>();
        Integer key = new Integer(1);
        String value = "HasMap";

        map.put(key, value);
        System.out.println(map);

        key = null;
        System.out.println(map);

        System.gc();
        System.out.println(map+"\t"+map.size());
    }

    private static void myWeakHasMap()
    {
        WeakHashMap<Integer, String> map = new WeakHashMap<>();
        Integer key = new Integer(2);
        String value = "HasMap";

        map.put(key, value);
        System.out.println(map);

        key = null;
        System.out.println(map);

        System.gc();
        System.out.println(map+"\t"+map.size());
    }
}
