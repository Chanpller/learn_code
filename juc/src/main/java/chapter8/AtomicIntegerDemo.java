package chapter8;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
class MyNumber{
    AtomicInteger atomicInteger = new AtomicInteger();
    public void addPlusPlus(){
        atomicInteger.getAndIncrement();
    }
}
public class AtomicIntegerDemo {
    public static int size = 50;
    public static void main(String[] args) {
        MyNumber myNumber = new MyNumber();
        for (int i=0;i<size;i++){
            new Thread(()->{
                    myNumber.addPlusPlus();
            }).start();
        }
        //等待线程执行完成
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        System.out.println(Thread.currentThread().getName()+"\t result:"+myNumber.atomicInteger.get());
    }
}
