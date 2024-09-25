package chapter9;

public class ThreadTest {
    public static void main(String[] args) {
        ThreadLocal<Integer> threadLocal = ThreadLocal.withInitial(()->0);
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    threadLocal.set(5);
                    Thread.sleep(30000*100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("开始了");
    }
}
