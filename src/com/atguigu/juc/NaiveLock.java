package com.atguigu.juc;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

/**
 * <p>简单描述该类的作用</p>
 *
 * @author xuqinghui
 * @create 2021/7/13 16:14
 */
public class NaiveLock {

    private static final long NONE=-1;
    private long owner =NONE;

    public synchronized void lock(){
        long currentThreadId=Thread.currentThread().getId();
        if(owner==currentThreadId){
            throw new IllegalStateException("lock has been acquired by current thread");
        }

        while(this.owner!=NONE){
            System.out.println(String.format("thread %s is waiting lock", currentThreadId));
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.owner=currentThreadId;
        System.out.println(String.format("lock is acquired by thread %s", currentThreadId));

    }

    public synchronized void unlock(){

        long currentThreadId=Thread.currentThread().getId();

        if(this.owner!=currentThreadId){
            throw new IllegalStateException("Only lock owner can unlock the lock");
        }

        System.out.println(String.format("thread %s is unlocking", owner));
        owner=NONE;
        notify();

    }

    public static void main(String[] args) {
           final NaiveLock lock=new NaiveLock();
           ExecutorService executor= Executors.newFixedThreadPool(20, new ThreadFactory(){
           private ThreadGroup group =new ThreadGroup("test thread group");
           {
               group.setDaemon(true);
           }
           @Override
           public Thread newThread(Runnable r) {
               return new Thread(group,r);
           }
           });


        for(int i=0;i<20;i++){
            executor.submit(new Runnable(){
                @Override
                public void run() {
                    lock.lock();
                    System.out.println(String.format("thread %s is running...", Thread.currentThread().getId()));

                    try {
                        Thread.sleep(new Random().nextInt(1000));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    lock.unlock();
                }
            });


        }


    }

}
