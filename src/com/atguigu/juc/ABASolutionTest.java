package com.atguigu.juc;

import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * <p>简单描述该类的作用</p>
 *
 * @author xuqinghui
 * @create 2019/12/25 11:41
 */
public class ABASolutionTest {

	private static AtomicStampedReference<Integer> atomicStampedRef = new AtomicStampedReference<>(1, 0);

	public static void main(String[] args){
		Thread main = new Thread(() -> {
			System.out.println("操作线程" + Thread.currentThread() +",初始值 a = " + atomicStampedRef.getReference());
			int stamp = atomicStampedRef.getStamp(); //获取当前标识别
			try {
				Thread.sleep(1000); //等待1秒 ，以便让干扰线程执行
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			boolean isCASSuccess = atomicStampedRef.compareAndSet(1,2,stamp,stamp +1);  //此时expectedReference未发生改变，但是stamp已经被修改了,所以CAS失败
			System.out.println("操作线程" + Thread.currentThread() +",CAS操作结果: " + isCASSuccess);
		},"主操作线程");

		Thread other = new Thread(() -> {
			Thread.yield(); // 确保thread-main 优先执行
			atomicStampedRef.compareAndSet(1,2,atomicStampedRef.getStamp(),atomicStampedRef.getStamp() +1);
			System.out.println("操作线程" + Thread.currentThread() +",【increment】 ,值 = "+ atomicStampedRef.getReference());
			atomicStampedRef.compareAndSet(2,1,atomicStampedRef.getStamp(),atomicStampedRef.getStamp() +1);
			System.out.println("操作线程" + Thread.currentThread() +",【decrement】 ,值 = "+ atomicStampedRef.getReference());
		},"干扰线程");

		main.start();
		other.start();
	}

	/*
	* JAVA中ABA中解决方案(AtomicStampedReference/AtomicMarkableReference)

	AtomicStampedReference 本质是有一个int 值作为版本号，每次更改前先取到这个int值的版本号，等到修改的时候，
	比较当前版本号与当前线程持有的版本号是否一致，如果一致，则进行修改，并将版本号+1（当然加多少或减多少都是可以自己定义的），
	在zookeeper中保持数据的一致性也是用的这种方式；

	AtomicMarkableReference则是将一个boolean值作是否有更改的标记，本质就是它的版本号只有两个，true和false，
	修改的时候在这两个版本号之间来回切换，这样做并不能解决ABA的问题，只是会降低ABA问题发生的几率而已；
	*/

}

