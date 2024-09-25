package chapter9;

import java.lang.ref.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

class MyObject{
    byte[] bytes = new byte[20 * 1024 * 1024];//20MB对象
}
//-Xmx60m
public class SoftReferenceDemo {
    public static void main(String[] args) {
        MyObject myObject = new MyObject();
        ReferenceQueue<MyObject> referenceQueue = new ReferenceQueue<>();
        PhantomReference<MyObject> phantomReference = new PhantomReference<>(myObject,referenceQueue);
        System.out.println(phantomReference.get());

        List<byte[]> list = new ArrayList<>();

        new Thread(() -> {
            while (true){
                list.add(new byte[1 * 1024 * 1024]);
                try { TimeUnit.MILLISECONDS.sleep(500); } catch (InterruptedException e) { e.printStackTrace(); }
                System.out.println(phantomReference.get()+"\t"+"list add ok");
            }
        },"t1").start();

        new Thread(() -> {
            while (true){
                Reference<? extends MyObject> reference = referenceQueue.poll();
                if(reference != null){
                    System.out.println("-----有虚对象回收加入了队列"+reference);
                    break;
                }
            }
        },"t2").start();

//        WeakReference<MyObject> weakReference = new WeakReference(new MyObject());
//        System.gc();
//        try {
//            Thread.sleep(5000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//        System.out.println("内存充足时：对象"+weakReference.get());
//        try {
//            MyObject myObject = new MyObject();
//        }catch (Throwable e){
//            e.printStackTrace();
//        }finally {
//            System.out.println("内存不充足时：对象"+weakReference.get());
//        }

//        SoftReference softReference = new SoftReference(new MyObject());
//        System.gc();
//        try {
//            Thread.sleep(5000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//        System.out.println("内存充足时：对象"+softReference.get());
//        try {
//            MyObject myObject = new MyObject();
//        }catch (Throwable e){
//            e.printStackTrace();
//        }finally {
//            System.out.println("内存不充足时：对象"+softReference.get());
//        }

    }
}
