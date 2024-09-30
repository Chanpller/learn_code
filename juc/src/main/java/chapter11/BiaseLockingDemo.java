package chapter11;

import org.openjdk.jol.info.ClassLayout;

import java.util.concurrent.CountDownLatch;

//实际上偏向锁在JDK1.6之后是默认开启的，但是启动时间有延迟，
//        所以需要添加参数-XX:BiasedLockingStartupDelay=0,让其在程序启动时立刻启动。
//        开启偏向锁：-XX:+UseBiasedLocking -XX:BiasedLockingStartupDelay=0
//        关闭偏向锁：关闭之后程序默认会直接进入轻量级锁状态。
//        －XX: -UseBiasedLocking
public class BiaseLockingDemo {
    public static void main(String[] args) {
        //延迟5秒后，对象默认的就是偏向锁状态
//        try {
//            Thread.sleep(5000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

//        Object object = new Object();
//        System.out.println(ClassLayout.parseInstance(object).toPrintable());
//
//        new Thread(()->{
//            synchronized (object){
//                System.out.println(ClassLayout.parseInstance(object).toPrintable());
//            }
//        }).start();

        twoThreadMarkWordStat();

//        thenCallHasCodeMethodCannotInBiaseLock();
//
//        biaseLockPrd();
    }
//开启偏向锁：-XX:+UseBiasedLocking -XX:BiasedLockingStartupDelay=0
    public static void twoThreadMarkWordStat(){
        Object object = new Object();
        System.out.println(Thread.currentThread().getName()+ClassLayout.parseInstance(object).toPrintable());
        CountDownLatch countDownLatch = new CountDownLatch(1);
        new Thread(()->{
            try {
                countDownLatch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            for (int i=0;i<2;i++){
                synchronized (object){
                     int b = 1+3;
                    System.out.println(Thread.currentThread().getName()+ClassLayout.parseInstance(object).toPrintable());
                }
            }
        }).start();

        new Thread(()->{
            try {
                countDownLatch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            for (int i=0;i<2;i++){
                synchronized (object){
                    int b = 1+3;
                }
            }
        }).start();
        countDownLatch.countDown();
    }

    //开启偏向锁：-XX:+UseBiasedLocking -XX:BiasedLockingStartupDelay=0
    public static void thenCallHasCodeMethodCannotInBiaseLock(){
        Object object = new Object();
        System.out.println(Thread.currentThread().getName()+ClassLayout.parseInstance(object).toPrintable());
        object.hashCode();
        System.out.println(Thread.currentThread().getName()+ClassLayout.parseInstance(object).toPrintable());
        new Thread(()->{
                synchronized (object){
                    System.out.println(Thread.currentThread().getName()+ClassLayout.parseInstance(object).toPrintable());
                }
        }).start();
    }
    //开启偏向锁：-XX:+UseBiasedLocking -XX:BiasedLockingStartupDelay=0
    public static void biaseLockPrd(){
        Object object = new Object();
        System.out.println(Thread.currentThread().getName()+ClassLayout.parseInstance(object).toPrintable());
        new Thread(()->{
            synchronized (object){
                System.out.println(Thread.currentThread().getName()+ClassLayout.parseInstance(object).toPrintable());
                object.hashCode();
                System.out.println(Thread.currentThread().getName()+ClassLayout.parseInstance(object).toPrintable());
            }
        }).start();
    }
}
