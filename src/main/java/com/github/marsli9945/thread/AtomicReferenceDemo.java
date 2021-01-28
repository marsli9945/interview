package com.github.marsli9945.thread;

import java.util.concurrent.atomic.AtomicReference;

public class AtomicReferenceDemo
{
    public static void main(String[] args)
    {
        User z3 = new User("z3", 22);
        User l4 = new User("l4", 25);

        AtomicReference<User> objectAtomicReference = new AtomicReference<>();
        objectAtomicReference.set(z3);
        System.out.println(objectAtomicReference.compareAndSet(z3, l4) + "\t" + objectAtomicReference.get().toString());
        System.out.println(objectAtomicReference.compareAndSet(z3, l4) + "\t" + objectAtomicReference.get().toString());
    }
}
