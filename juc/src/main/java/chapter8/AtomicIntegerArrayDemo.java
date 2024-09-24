package chapter8;

import java.util.concurrent.atomic.AtomicIntegerArray;

public class AtomicIntegerArrayDemo {
    public static void main(String[] args) {
        AtomicIntegerArray atomicIntegerArray = new AtomicIntegerArray(new int[5]);
        for (int i=0;i<atomicIntegerArray.length();i++){
            System.out.println(atomicIntegerArray.get(i));
        }
        System.out.println(atomicIntegerArray.getAndSet(0,8));
        System.out.println(atomicIntegerArray.get(0));
        System.out.println(atomicIntegerArray.incrementAndGet(0));
        System.out.println(atomicIntegerArray.get(0));
    }
}
