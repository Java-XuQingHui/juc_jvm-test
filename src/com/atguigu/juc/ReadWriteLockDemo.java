package com.atguigu.juc;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * <p>读写锁</p>
 *
 * @author xuqinghui
 * @create 2020/4/24 11:04
 */

// 资源类

class MyCache {
	private volatile Map<String,Object> map = new HashMap<>();

	private ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

	public void put(String key, Object value) {
		readWriteLock.writeLock().lock();

		try {
			System.out.println(Thread.currentThread().getName() + "\t" + "----写入数据-----" + key);
			TimeUnit.MILLISECONDS.sleep(300);
			map.put(key,value);
			System.out.println(Thread.currentThread().getName() + "\t" + "----写入完成-----");
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			readWriteLock.writeLock().unlock();
		}
	}

	public void get(String key) {
		readWriteLock.readLock().lock();

		try {
			System.out.println(Thread.currentThread().getName() + "\t" + "----读取数据-----");
			TimeUnit.MILLISECONDS.sleep(300);
			Object result = map.get(key);
			System.out.println(Thread.currentThread().getName() + "\t" + "----读取完成-----" + result);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			readWriteLock.readLock().unlock();
		}
	}
}
public class ReadWriteLockDemo {

	public static void main(String[] args) {
		MyCache myCache = new MyCache();

		for (int i = 1; i <= 5 ; i++) {
			final int tempInt = i;
			new Thread(() -> {
				myCache.put(tempInt + "",tempInt + "");
			},String.valueOf(i)).start();
		}

		for (int i = 1; i <= 5 ; i++) {
			final int tempInt = i;
			new Thread(() -> {
				myCache.get(tempInt + "");
			},String.valueOf(i)).start();
		}
	}
}

/**
 * 多个线程同时读一个资源类没有任何问题，所以为了满足并发量，读取共享资源应该可以同时进行
 * 但是，如果有一个线程想去写共享资源类，就不应该再有其他线程可以对资源类进行读写操作了
 *  总结： 读-读能共存 读-写不能共存 写-写不能共存*/