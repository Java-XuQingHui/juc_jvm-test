package com.atguigu.juc;

/**
 * <p>简单描述该类的作用</p>
 *
 * @author xuqinghui
 * @create 2021/7/13 15:13
 */
public class SyncJCTest {

    /**
     * 显而易见，我们可以得出结论，加入父类方法中有synchronized关键字修饰，
     * 子类继承该父类的方法也有Synchronizde关键字的效果，
     * 但是如果重写该方法时不显式加上synchronized关键字时则不会有效果
     *
     * */

    public static void main(String[] args) throws InterruptedException {
        //SyncJCTest jcTest = new SyncJCTest();

//        SubSyncTest subSyncTest = new SubSyncTest();
//
//        for (int i = 0; i < 5; i++) {
//            new Thread(new Runnable() {
//                @Override
//                public void run() {
//                    subSyncTest.doSomething();
//                }
//            }).start();
//        }

        // 下面子类调用父类方法，和在自己类的方法是都是同一把锁，子类作为锁对象
        SubSyncTest subSyncTest = new SubSyncTest();
        new Thread(new Runnable() {
            @Override
            public void run() {
                subSyncTest.doSomething();
            }
        }).start();
        Thread.sleep(100);
        subSyncTest.doSomethingElse();
    }

    public synchronized void doSomething() {
        System.out.println("AA--" + Thread.currentThread());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("BB--"+ Thread.currentThread());
    }

    static class SubSyncTest extends SyncJCTest {
        @Override
        public synchronized void doSomething() {
            super.doSomething();

//            System.out.println("AAA--" + Thread.currentThread());
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            System.out.println("BBB--"+ Thread.currentThread());
        }


        public synchronized void doSomethingElse() {
            System.out.println("something else");
        }
    }

}
