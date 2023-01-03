package com.azhe.coding.juc;

import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.concurrent.*;

/**
 * @author linzherong   14
 * @date 2022/12/11 23:45
 */
public class ThreadPoolExecutorDemo {

    private static Unsafe UNSAFE;
    private static int a;
    private static long A_OFFSET;

    static {
        try {

            Field theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
            Field fa = ThreadPoolExecutorDemo.class.getDeclaredField("a");
            theUnsafe.setAccessible(true);
            UNSAFE  = (Unsafe) theUnsafe.get(null);
            A_OFFSET = UNSAFE.staticFieldOffset(fa);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static void incr() {
        for (;;) {
            int i = a;
            if(UNSAFE.compareAndSwapInt(ThreadPoolExecutorDemo.class, A_OFFSET, i, ++i)) {
                break;
            }
            Thread.yield();
        }
    }

    static class MyExecutor extends ThreadPoolExecutor {

        public MyExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
            super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
        }

        @Override
        protected void afterExecute(Runnable r, Throwable t) {
//            int i = 1/0;
        }
    }

    public static void main(String[] args) throws Exception {

//        for (int i = 0; i < 100000; i++) {
//            new Thread(ThreadPoolExecutorDemo::incr).start();
//        }
//        TimeUnit.MILLISECONDS.sleep(100);
//        System.out.println(a);

//        MyExecutor myExecutor = new MyExecutor(1, 1, 30, TimeUnit.SECONDS, new LinkedBlockingQueue<>());
//
//        myExecutor.execute(()-> System.out.println(Thread.currentThread().getName()));
//        myExecutor.execute(()-> System.out.println(Thread.currentThread().getName()));
//        myExecutor.execute(()-> System.out.println(Thread.currentThread().getName()));
//        myExecutor.execute(()-> System.out.println(Thread.currentThread().getName()));
//        myExecutor.execute(()-> System.out.println(Thread.currentThread().getName()));

        // 定时线程池做调度
        ScheduledExecutorService scheduledThreadPoolExecutor = new ScheduledThreadPoolExecutor(5);
//        // 具体执行业务的线程池
//        ExecutorService executorService = Executors.newCachedThreadPool();
//
//        // 用执行线程池执行业务，定时线程池不会阻塞
//        scheduledThreadPoolExecutor.schedule(()->executorService.execute(()-> System.out.println(1)), 1, TimeUnit.SECONDS);
//        scheduledThreadPoolExecutor.schedule(()->executorService.execute(()-> System.out.println(1)), 1, TimeUnit.SECONDS);
//        scheduledThreadPoolExecutor.schedule(()->executorService.execute(()-> System.out.println(1)), 1, TimeUnit.SECONDS);
//        scheduledThreadPoolExecutor.schedule(()->executorService.execute(()-> System.out.println(1)), 1, TimeUnit.SECONDS);
//        scheduledThreadPoolExecutor.schedule(()->executorService.execute(()-> System.out.println(1)), 1, TimeUnit.SECONDS);

        scheduledThreadPoolExecutor.schedule(()-> System.out.println(1), 1, TimeUnit.SECONDS);
        scheduledThreadPoolExecutor.scheduleAtFixedRate(()-> System.out.println(1), 1, 1, TimeUnit.SECONDS);
        scheduledThreadPoolExecutor.scheduleWithFixedDelay(()-> System.out.println(1), 1, 1, TimeUnit.SECONDS);
//        scheduledThreadPoolExecutor.shutdown();
    }

}
