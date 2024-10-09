package chapter13;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

class MyResource{
    //资源类，模拟一个简单的缓存
    Map<String,String> map  = new HashMap<>();

    //ReentrantLock 等价于synchronized
    Lock lock = new ReentrantLock();

    //ReentrantReadWriteLock 读写互斥，读读共享
    ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    public  void  write(String key,String value){
        readWriteLock.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName()+"\t正在写入");
            map.put(key,value);
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+"\t完成写入");
        }finally {
            readWriteLock.writeLock().unlock();
        }

    }
    public  void  read(String key){
        readWriteLock.readLock().lock();
        try {
            System.out.println(Thread.currentThread().getName()+"\t正在读取");
            String s = map.get(key);
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+"\t完成读取\t"+s);
        }finally {
            readWriteLock.readLock().unlock();
        }

    }
}
public class ReentrantReadWriteLockDemo {
    public static void main(String[] args)
    {
        MyResource myResource = new MyResource();

        for (int i = 1; i <=10; i++) {
            int finalI = i;
            new Thread(() -> {
                myResource.write(finalI +"", finalI +"");
            },String.valueOf(i)).start();
        }

        for (int i = 1; i <=10; i++) {
            int finalI = i;
            new Thread(() -> {
                myResource.read(finalI +"");
            },String.valueOf(i)).start();
        }

        //暂停几秒钟线程
        try { TimeUnit.SECONDS.sleep(1); } catch (InterruptedException e) { e.printStackTrace(); }

        for (int i = 1; i <=3; i++) {
            int finalI = i;
            new Thread(() -> {
                myResource.write(finalI +"", finalI +"");
            },"新写锁线程->"+String.valueOf(i)).start();
        }
    }
}
