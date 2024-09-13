package chapter6;

import java.util.concurrent.atomic.AtomicInteger;

public class CasDemo {
    public static void main(String[] args) {
        AtomicInteger atomicInteger  = new AtomicInteger(5);
        boolean setOk = atomicInteger.compareAndSet(5, 200);
        System.out.println("更新："+setOk+",新值："+atomicInteger.get());
        setOk = atomicInteger.compareAndSet(5, 300);
        System.out.println("更新："+setOk+",新值："+atomicInteger.get());
    }
}
