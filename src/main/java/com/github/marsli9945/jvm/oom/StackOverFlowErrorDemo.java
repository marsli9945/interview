package com.github.marsli9945.jvm.oom;

public class StackOverFlowErrorDemo
{
    public static void main(String[] args)
    {
        stackOverFlowError();
    }

    private static void stackOverFlowError()
    {
        stackOverFlowError();//Exception in thread "main" java.lang.stackOverFlowError
    }
}
