package com.ke.basic.ThreadPool;

import java.util.concurrent.*;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * https://www.cnblogs.com/thisiswhy/p/17994272
 *
 * @author zhangxudong
 * @since 2024/1/29 21:52
 */
public class ThreadPoolTest {
	/**
	 * 大概解释：请求进来之后要先创建核心线程，即使有处于空闲状态的核心线程，只要数目不到corePoolSize,都要进行创建。核心线程数目到达corePoolSize之后，就把任务往队列放，
	 * 核心线程处理完上一个任务后会从队列获取下一个任务。队列获取的存放任务是加锁的。当队列小于一次请求的并发数目时，就会出现问题。
	 * 后续措施：尽量不要把线程池的核心线程数和最大线程数设置的一样，把阻塞队列的长度设置得大一些，至少保证阻塞队列本身的长度大于一次提交进来的任务数，
	 * 而不要做出线程数加上队列长度才勉强容纳单批次任务数，这么极端的长度参数。
	 */
	private static final ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(64, 64, 0, TimeUnit.MINUTES, new ArrayBlockingQueue<>(33));

	@org.testng.annotations.Test
	public void test1() {
		for (int i = 0; i < 100; i++) {
			CountDownLatch countDownLatch = new CountDownLatch(34);
			for (int j = 0; j < 34; j++) {
				threadPoolExecutor.execute(() -> {
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						throw new RuntimeException(e);
					} finally {
						countDownLatch.countDown();
					}
				});
			}
			try {
				countDownLatch.await();
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
			System.out.println("任务处理完成:" + i);
		}
	}


	@org.testng.annotations.Test
	public void test2() throws InterruptedException {
		//销毁时间设置的很长
		ThreadPoolExecutor threadPoolExecutor2 = new ThreadPoolExecutor(20, 200, 100, TimeUnit.MINUTES, new ArrayBlockingQueue<>(33));
		for (int i = 0; i < 10; i++) {
			final int j = i;
			threadPoolExecutor2.execute(() -> System.out.println(j));
		}
		System.out.println("线程数" + threadPoolExecutor2.getPoolSize());
		System.out.println("队列数目" + threadPoolExecutor2.getQueue().size());
		Thread.sleep(1000 * 5);

		for (int i = 0; i < 10; i++) {
			final int j = i;
			threadPoolExecutor2.execute(() -> System.out.println(j));
		}
		System.out.println("线程数" + threadPoolExecutor2.getPoolSize());
		System.out.println("队列数目" + threadPoolExecutor2.getQueue().size());


		/**
		 * 以上验证了核心线程的数目不会因为有空闲线程就复用，而是创建新的线程直到核心线程满了，这个时候就会往队列里面塞线程
		 * 从另一方面来看，线程池对于任务和线程状态是解偶的
		 */

		for (int i = 0; i < 10; i++) {
			final int j = i;
			threadPoolExecutor2.execute(() -> System.out.println(j));
		}
		System.out.println("线程数" + threadPoolExecutor2.getPoolSize());
		System.out.println("队列数目" + threadPoolExecutor2.getQueue().size());


	}

	@org.testng.annotations.Test
	public void test(){
		//submit 可以提交runnable callable方法
		//submit方法，实际上是将任务封装成一个FutureTask对象，然后使用execute方法将其提交给线程池。
		//FutureTask.run方法中，如果Callable任务抛出异常，这个异常会被捕获并通过setException方法保存。这个异常不会导致执行任务的线程终止，因为异常被FutureTask内部处理了。
		Executors.newFixedThreadPool(10).submit(()->{
			System.out.println(1);
			return null;
		});

		//execute 不可以提交callable方法，可以提交runnable
		Executors.newFixedThreadPool(10).execute(()->{
			System.out.println(2);
		});
	}



	//DiscardPolicy的任务不会执行线程内部的逻辑，futureTask的get方法会一致阻塞
	@org.testng.annotations.Test
	public void test3() throws ExecutionException, InterruptedException {
		// 一个核心线程，队列最大为1，最大线程数也是1.拒绝策略是DiscardPolicy
		ThreadPoolExecutor executorService = new ThreadPoolExecutor(1, 1, 1L, TimeUnit.MINUTES,
				new ArrayBlockingQueue<>(1), new ThreadPoolExecutor.DiscardPolicy());

		Future f1 = executorService.submit(() -> {
			System.out.println("提交任务1");
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});

		Future f2 = executorService.submit(() -> {
			System.out.println("提交任务2");
		});

		Future f3 = executorService.submit(() -> {
			System.out.println("提交任务3");
		});

		System.out.println("任务1完成 " + f1.get());// 等待任务1执行完毕
		System.out.println("任务2完成" + f2.get());// 等待任务2执行完毕
		System.out.println("任务3完成" + f3.get());// 等待任务3执行完毕

	}

}
