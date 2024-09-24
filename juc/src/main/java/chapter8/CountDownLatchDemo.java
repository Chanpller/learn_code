package chapter8;

import java.util.concurrent.CountDownLatch;
public class CountDownLatchDemo {
    public static int size = 50;
    public static void main(String[] args) {
        CountDownLatch countDownLatch = new CountDownLatch(size);
        MyNumber myNumber = new MyNumber();
        for (int i=0;i<size;i++){
            new Thread(()->{
                try {
                    for (int j=0;j<10;j++){
                        myNumber.addPlusPlus();
                    }
                }finally {
                    countDownLatch.countDown();
                }
            }).start();
        }
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        System.out.println(Thread.currentThread().getName()+"\t result:"+myNumber.atomicInteger.get());
    }
}

