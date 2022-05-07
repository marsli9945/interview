package com.github.marsli9945.jvm.oom;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * -XX:MetaspaceSize=10m -XX:MaxMetaspaceSize=10m
 */
public class MetaspaceDemo
{
    static class OOMTest{}

    public static void main(String[] args)
    {
        int i = 0;//模拟多少次后发生异常

        try
        {
            while (true) {
                i++;
                final Enhancer enhancer = new Enhancer();
                enhancer.setSuperclass(OOMTest.class);
                enhancer.setUseCache(false);
                enhancer.setCallback(new MethodInterceptor()
                {
                    @Override
                    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable
                    {
                        return methodProxy.invokeSuper(o,args);
                    }
                });
                enhancer.create();
            }
        }catch (Throwable e) {
            System.out.println("*************多少次后发生异常"+i);
            e.printStackTrace();
        }
        // org.springframework.cglib.core.CodeGenerationException: java.lang.OutOfMemoryError-->Metaspace
    }
}
