package chapter8;

import java.util.concurrent.atomic.LongAccumulator;

public class LongAccumulatorDemo {
    public static void main(String[] args) {
        LongAccumulator longAccumulator = new LongAccumulator((x,y)->x+y,0);
        longAccumulator.accumulate(5);
        System.out.println(longAccumulator.get());
        longAccumulator.accumulate(6);
        System.out.println(longAccumulator.get());
        longAccumulator.accumulate(7);
        System.out.println(longAccumulator.get());
    }
}
