package com.atguigu.juc;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * <p>集合不安全</p>
 *
 * @author xuqinghui
 * @create 2020/4/17 17:47
 */

// 1.故障现象 ConcurrentModificationException

// 2.导致原因

// 3.解决方法
// new Vector<>();
// Collections.synchronizedList(new ArrayList<>());
// new CopyOnWriteArrayList<>() ; Copy-On-Write，写入时复制

// 4.优化建议
public class NotSafeDemo {

	public static void main(String[] args) {
		mapNotSafe();
	}

	public static void mapNotSafe() {
		Map<String, String> map = new ConcurrentHashMap<>();
		//new Hashtable<>();
		//new HashMap<>();

		for (int i = 1; i <= 30 ; i++) {
			new Thread(() -> {
				map.put(Thread.currentThread().getName(), UUID.randomUUID().toString().substring(0, 8));
				System.out.println(map);
			},String.valueOf(i)).start();
		}
	}

	public static void setNotSafe() {
		Set<String> set = new CopyOnWriteArraySet<>();
		// Collections.synchronizedSet(new HashSet<>());
		// new HashSet<>();

		for (int i = 1; i <= 30 ; i++) {
			new Thread(() -> {
				set.add(UUID.randomUUID().toString().substring(0, 8));
				System.out.println(set);
			},String.valueOf(i)).start();
		}
	}

	public static void listNotSafe() {
		List<String> list = new CopyOnWriteArrayList<>();
		//Collections.synchronizedList(new ArrayList<>());
		// new Vector<>();
		// new ArrayList<>();

		for (int i = 1; i <= 30 ; i++) {
			new Thread(() -> {
				list.add(UUID.randomUUID().toString().substring(0, 8));
				System.out.println(list);
			},String.valueOf(i)).start();
		}
	}

}

/* 写时复制：
*
*    CopyOnWrite容器即写时复制的容器，往一个容器中添加元素的时候，不直接往当前容器 Object[]添加，而是将当前容器Object[]进行copy，
*    复制出一个新的容器Object[] newElements，然后新的容器Object[] newElements里添加元素，添加完元素之后，再将原容器的引用执行新
*    的容器 setArray(newElements)，这样做的好处是可以对CopyOnWrite容器进行并发的读，而不需要加锁，因为当前容器不会添加任何元素。
*    所以CopyOnWrite容器也是一种读写分离的思想，读和写不同的容器。
*
*   public boolean add(E e) {
    final ReentrantLock lock = this.lock;
    lock.lock();
    try {
        Object[] elements = getArray();
        int len = elements.length;
        Object[] newElements = Arrays.copyOf(elements, len + 1);
        newElements[len] = e;
        setArray(newElements);
        return true;
    } finally {
        lock.unlock();
    }
*/