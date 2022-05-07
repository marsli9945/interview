package com.github.marsli9945.jvm.ref;

/**
 * 强引用示例
 */
public class StrongReferenceDemo
{
    public static void main(String[] args)
    {
        Object obj1 = new Object();//这样定义默认就是强引用
        Object obj2 = obj1;//obj2引用赋值
        obj1 = null;//置空
        System.gc();
        System.out.println(obj2);
    }
}
