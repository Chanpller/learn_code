//package chapter8;/*
// * ORACLE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
// *
// *
// *
// *
// *
// *
// *
// *
// *
// *
// *
// *
// *
// *
// *
// *
// *
// *
// *
// *
// */
//
///*
// *
// *
// *
// *
// *
// * Written by Doug Lea with assistance from members of JCP JSR-166
// * Expert Group and released to the public domain, as explained at
// * http://creativecommons.org/publicdomain/zero/1.0/
// */
//
//import java.util.function.LongBinaryOperator;
//import java.util.function.DoubleBinaryOperator;
//import java.util.concurrent.ThreadLocalRandom;
//
///**
// * A package-local class holding common representation and mechanics
// * for classes supporting dynamic striping on 64bit values. The class
// * extends Number so that concrete subclasses must publicly do so.
// */
//@SuppressWarnings("serial")
//abstract class MyStriped64 extends Number {
//    /*
//     * This class maintains a lazily-initialized table of atomically
//     * updated variables, plus an extra "base" field. The table size
//     * is a power of two. Indexing uses masked per-thread hash codes.
//     * Nearly all declarations in this class are package-private,
//     * accessed directly by subclasses.
//     *
//     * Table entries are of class Cell; a variant of AtomicLong padded
//     * (via @sun.misc.Contended) to reduce cache contention. Padding
//     * is overkill for most Atomics because they are usually
//     * irregularly scattered in memory and thus don't interfere much
//     * with each other. But Atomic objects residing in arrays will
//     * tend to be placed adjacent to each other, and so will most
//     * often share cache lines (with a huge negative performance
//     * impact) without this precaution.
//     *
//     * In part because Cells are relatively large, we avoid creating
//     * them until they are needed.  When there is no contention, all
//     * updates are made to the base field.  Upon first contention (a
//     * failed CAS on base update), the table is initialized to size 2.
//     * The table size is doubled upon further contention until
//     * reaching the nearest power of two greater than or equal to the
//     * number of CPUS. Table slots remain empty (null) until they are
//     * needed.
//     *
//     * A single spinlock ("cellsBusy") is used for initializing and
//     * resizing the table, as well as populating slots with new Cells.
//     * There is no need for a blocking lock; when the lock is not
//     * available, threads try other slots (or the base).  During these
//     * retries, there is increased contention and reduced locality,
//     * which is still better than alternatives.
//     *
//     * The Thread probe fields maintained via ThreadLocalRandom serve
//     * as per-thread hash codes. We let them remain uninitialized as
//     * zero (if they come in this way) until they contend at slot
//     * 0. They are then initialized to values that typically do not
//     * often conflict with others.  Contention and/or table collisions
//     * are indicated by failed CASes when performing an update
//     * operation. Upon a collision, if the table size is less than
//     * the capacity, it is doubled in size unless some other thread
//     * holds the lock. If a hashed slot is empty, and lock is
//     * available, a new Cell is created. Otherwise, if the slot
//     * exists, a CAS is tried.  Retries proceed by "double hashing",
//     * using a secondary hash (Marsaglia XorShift) to try to find a
//     * free slot.
//     *
//     * The table size is capped because, when there are more threads
//     * than CPUs, supposing that each thread were bound to a CPU,
//     * there would exist a perfect hash function mapping threads to
//     * slots that eliminates collisions. When we reach capacity, we
//     * search for this mapping by randomly varying the hash codes of
//     * colliding threads.  Because search is random, and collisions
//     * only become known via CAS failures, convergence can be slow,
//     * and because threads are typically not bound to CPUS forever,
//     * may not occur at all. However, despite these limitations,
//     * observed contention rates are typically low in these cases.
//     *
//     * It is possible for a Cell to become unused when threads that
//     * once hashed to it terminate, as well as in the case where
//     * doubling the table causes no thread to hash to it under
//     * expanded mask.  We do not try to detect or remove such cells,
//     * under the assumption that for long-running instances, observed
//     * contention levels will recur, so the cells will eventually be
//     * needed again; and for short-lived ones, it does not matter.
//     */
//
//    /**
//     * Padded variant of AtomicLong supporting only raw accesses plus CAS.
//     *
//     * JVM intrinsics note: It would be possible to use a release-only
//     * form of CAS here, if it were provided.
//     */
////    @sun.misc.Contended static final class Cell {
//     static final class Cell {
//        volatile long value;
//        Cell(long x) { value = x; }
//        final boolean cas(long cmp, long val) {
//            return UNSAFE.compareAndSwapLong(this, valueOffset, cmp, val);
//        }
//
//        // Unsafe mechanics
//        private static final sun.misc.Unsafe UNSAFE;
//        private static final long valueOffset;
//        static {
//            try {
//                UNSAFE = sun.misc.Unsafe.getUnsafe();
//                Class<?> ak = Cell.class;
//                valueOffset = UNSAFE.objectFieldOffset
//                        (ak.getDeclaredField("value"));
//            } catch (Exception e) {
//                throw new Error(e);
//            }
//        }
//    }
//
//    /** Number of CPUS, to place bound on table size */
//    static final int NCPU = Runtime.getRuntime().availableProcessors();
//
//    /**
//     * Table of cells. When non-null, size is a power of 2.
//     */
//    transient volatile Cell[] cells;
//
//    /**
//     * Base value, used mainly when there is no contention, but also as
//     * a fallback during table initialization races. Updated via CAS.
//     */
//    transient volatile long base;
//
//    /**
//     * Spinlock (locked via CAS) used when resizing and/or creating Cells.
//     */
//    transient volatile int cellsBusy;
//
//    /**
//     * Package-private default constructor
//     */
//    MyStriped64() {
//    }
//
//    /**
//     * CASes the base field.
//     */
//    final boolean casBase(long cmp, long val) {
//        return UNSAFE.compareAndSwapLong(this, BASE, cmp, val);
//    }
//
//    /**
//     * CASes the cellsBusy field from 0 to 1 to acquire lock.
//     */
//    final boolean casCellsBusy() {
//        return UNSAFE.compareAndSwapInt(this, CELLSBUSY, 0, 1);
//    }
//
//    /**
//     * Returns the probe value for the current thread.
//     * Duplicated from ThreadLocalRandom because of packaging restrictions.
//     */
//    static final int getProbe() {
//        return UNSAFE.getInt(Thread.currentThread(), PROBE);
//    }
//
//    /**
//     * Pseudo-randomly advances and records the given probe value for the
//     * given thread.
//     * Duplicated from ThreadLocalRandom because of packaging restrictions.
//     */
//    static final int advanceProbe(int probe) {
//        probe ^= probe << 13;   // xorshift
//        probe ^= probe >>> 17;
//        probe ^= probe << 5;
//        UNSAFE.putInt(Thread.currentThread(), PROBE, probe);
//        return probe;
//    }
//
//    /**
//     * Handles cases of updates involving initialization, resizing,
//     * creating new Cells, and/or contention. See above for
//     * explanation. This method suffers the usual non-modularity
//     * problems of optimistic retry code, relying on rechecked sets of
//     * reads.
//     *
//     * @param x the value
//     * @param fn the update function, or null for add (this convention
//     * avoids the need for an extra field or function in LongAdder).
//     * @param wasUncontended false if CAS failed before call
//     */
//    final void longAccumulate(long x, LongBinaryOperator fn,
//                              boolean wasUncontended) {
//        int h;
//        //获取当前线程hash值，如果不等于0，进行初始化，并获取hash值。
//        if ((h = getProbe()) == 0) {
//            ThreadLocalRandom.current(); // force initialization
//            h = getProbe();
//            wasUncontended = true;//线程有没有被忽略为true
//        }
//        boolean collide = false;  //如果插槽是最后一个，碰撞状态就是true              // True if last slot nonempty
//        for (;;) {
//            Cell[] as; Cell a; int n; long v;
//            //cell已经初始化，并且不为空。
//            if ((as = cells) != null && (n = as.length) > 0) {
//                if ((a = as[(n - 1) & h]) == null) {//线程hash值映射的cell为空，表示cell没有被使用
//                    if (cellsBusy == 0) {       // Try to attach new Cell
//                        Cell r = new Cell(x);   // Optimistically create
//                        if (cellsBusy == 0 && casCellsBusy()) {
//                            boolean created = false;
//                            try {               // Recheck under lock
//                                Cell[] rs; int m, j;
//                                if ((rs = cells) != null &&
//                                        (m = rs.length) > 0 &&
//                                        rs[j = (m - 1) & h] == null) {
//                                    rs[j] = r;
//                                    created = true;
//                                }
//                            } finally {
//                                cellsBusy = 0;
//                            }
//                            if (created)
//                                break;
//                            continue;           // Slot is now non-empty
//                        }
//                    }
//                    collide = false;
//                }
//                else if (!wasUncontended)       //wasUncontended表示前一次cas更新Cell单元是否成功
//                    wasUncontended = true;      // 重置为ture,后面会重新计算线程的hash值
//                else if (a.cas(v = a.value, ((fn == null) ? v + x :
//                        fn.applyAsLong(v, x))))//尝试更新Cell值，成功结束循环，不成功，再继续循环
//                    break;
//                else if (n >= NCPU || cells != as)//当数组大小超过CPU值或当前cells和局部变量不相同时(可能其他线程在操作)，不再进行扩容，
//                    collide = false;            // At max size or stale
//                else if (!collide)
//                    collide = true;
//                else if (cellsBusy == 0 && casCellsBusy()) {//Cell没有锁定，进行尝试锁定
//                    try {
//                        if (cells == as) {      //数据没有修改过，进行扩容
//                            Cell[] rs = new Cell[n << 1];//扩展两倍
//                            for (int i = 0; i < n; ++i)//数据进行复制
//                                rs[i] = as[i];
//                            cells = rs;//重新复制
//                        }
//                    } finally {
//                        cellsBusy = 0;//重新把操作cells标识改为0
//                    }
//                    collide = false;//扩容标记改为false
//                    continue;//重试
//                }
//                h = advanceProbe(h);//重新生成线程hash值
//            }
//            //cell没有初始化，没有被锁定，且cells没有被改变，尝试锁定cellsBusy值为锁定状态
//            else if (cellsBusy == 0 && cells == as && casCellsBusy()) {
//                boolean init = false;
//                try {                           // Initialize table
//                    if (cells == as) {//不二次校验，就会再new一个cell数组，上一个线程的值可能被串改
//                        Cell[] rs = new Cell[2];//创建2个cell
//                        rs[h & 1] = new Cell(x);//线程hash值找到对于的位置，把初始值放进去
//                        cells = rs;//复制出去
//                        init = true;
//                    }
//                } finally {
//                    cellsBusy = 0;
//                }
//                if (init)
//                    break;
//            }
//            //cell没有初始化，被锁定了，直接更新base值。
//            else if (casBase(v = base, ((fn == null) ? v + x :
//                    fn.applyAsLong(v, x))))
//                break;                          // Fall back on using base
//        }
//    }
//
//    /**
//     * Same as longAccumulate, but injecting long/double conversions
//     * in too many places to sensibly merge with long version, given
//     * the low-overhead requirements of this class. So must instead be
//     * maintained by copy/paste/adapt.
//     */
//    final void doubleAccumulate(double x, DoubleBinaryOperator fn,
//                                boolean wasUncontended) {
//        int h;
//        if ((h = getProbe()) == 0) {
//            ThreadLocalRandom.current(); // force initialization
//            h = getProbe();
//            wasUncontended = true;
//        }
//        boolean collide = false;                // True if last slot nonempty
//        for (;;) {
//            Cell[] as; Cell a; int n; long v;
//            if ((as = cells) != null && (n = as.length) > 0) {
//                if ((a = as[(n - 1) & h]) == null) {
//                    if (cellsBusy == 0) {       // Try to attach new Cell
//                        Cell r = new Cell(Double.doubleToRawLongBits(x));
//                        if (cellsBusy == 0 && casCellsBusy()) {
//                            boolean created = false;
//                            try {               // Recheck under lock
//                                Cell[] rs; int m, j;
//                                if ((rs = cells) != null &&
//                                        (m = rs.length) > 0 &&
//                                        rs[j = (m - 1) & h] == null) {
//                                    rs[j] = r;
//                                    created = true;
//                                }
//                            } finally {
//                                cellsBusy = 0;
//                            }
//                            if (created)
//                                break;
//                            continue;           // Slot is now non-empty
//                        }
//                    }
//                    collide = false;
//                }
//                else if (!wasUncontended)       // CAS already known to fail
//                    wasUncontended = true;      // Continue after rehash
//                else if (a.cas(v = a.value,
//                        ((fn == null) ?
//                                Double.doubleToRawLongBits
//                                        (Double.longBitsToDouble(v) + x) :
//                                Double.doubleToRawLongBits
//                                        (fn.applyAsDouble
//                                                (Double.longBitsToDouble(v), x)))))
//                    break;
//                else if (n >= NCPU || cells != as)
//                    collide = false;            // At max size or stale
//                else if (!collide)
//                    collide = true;
//                else if (cellsBusy == 0 && casCellsBusy()) {
//                    try {
//                        if (cells == as) {      // Expand table unless stale
//                            Cell[] rs = new Cell[n << 1];
//                            for (int i = 0; i < n; ++i)
//                                rs[i] = as[i];
//                            cells = rs;
//                        }
//                    } finally {
//                        cellsBusy = 0;
//                    }
//                    collide = false;
//                    continue;                   // Retry with expanded table
//                }
//                h = advanceProbe(h);
//            }
//            else if (cellsBusy == 0 && cells == as && casCellsBusy()) {
//                boolean init = false;
//                try {                           // Initialize table
//                    if (cells == as) {
//                        Cell[] rs = new Cell[2];
//                        rs[h & 1] = new Cell(Double.doubleToRawLongBits(x));
//                        cells = rs;
//                        init = true;
//                    }
//                } finally {
//                    cellsBusy = 0;
//                }
//                if (init)
//                    break;
//            }
//            else if (casBase(v = base,
//                    ((fn == null) ?
//                            Double.doubleToRawLongBits
//                                    (Double.longBitsToDouble(v) + x) :
//                            Double.doubleToRawLongBits
//                                    (fn.applyAsDouble
//                                            (Double.longBitsToDouble(v), x)))))
//                break;                          // Fall back on using base
//        }
//    }
//
//    // Unsafe mechanics
//    private static final sun.misc.Unsafe UNSAFE;
//    private static final long BASE;
//    private static final long CELLSBUSY;
//    private static final long PROBE;
//    static {
//        try {
//            UNSAFE = sun.misc.Unsafe.getUnsafe();
//            Class<?> sk =MyStriped64.class;
//            BASE = UNSAFE.objectFieldOffset
//                    (sk.getDeclaredField("base"));
//            CELLSBUSY = UNSAFE.objectFieldOffset
//                    (sk.getDeclaredField("cellsBusy"));
//            Class<?> tk = Thread.class;
//            PROBE = UNSAFE.objectFieldOffset
//                    (tk.getDeclaredField("threadLocalRandomProbe"));
//        } catch (Exception e) {
//            throw new Error(e);
//        }
//    }
//
//}
