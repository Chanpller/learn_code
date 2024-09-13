package chapter6;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.ReentrantLock;

public class SpinLockDemo {
    private static AtomicReference<Thread> threadAtomicReference = new AtomicReference<>();
    public void  lock(){
        Thread thread = Thread.currentThread();
        System.out.println(Thread.currentThread().getName() + "\t --------come in");
        while (!threadAtomicReference.compareAndSet(null,thread)){

        }
    }
    public void  unlock(){
        Thread thread = Thread.currentThread();
        threadAtomicReference.compareAndSet(thread,null);
        System.out.println(Thread.currentThread().getName() + "\t --------task over,unLock.........");
    }

    public static void main(String[] args) {
        SpinLockDemo spinLockDemo = new SpinLockDemo();
//        ReentrantLock spinLockDemo = new ReentrantLock();
        new Thread(() -> {
            //如果换ReentrantLock，报错
//            spinLockDemo.unlock();
            spinLockDemo.lock();
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            spinLockDemo.unlock();
        }, "A").start();


        try {
            TimeUnit.MILLISECONDS.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(() -> {
            spinLockDemo.lock();
            spinLockDemo.unlock();
        }, "B").start();
    }
}
