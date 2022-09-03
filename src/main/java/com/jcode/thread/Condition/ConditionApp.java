package com.jcode.thread.Condition;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionApp {

	public Lock lock = new ReentrantLock();
	public Condition condition = lock.newCondition();

	public static void main(String[] args) {
		final ConditionApp useCase = new ConditionApp();
		ExecutorService executorService = Executors.newFixedThreadPool(2);
		executorService.execute(new Runnable() {
		
			public void run() {
				useCase.conditionWait();
			}
		});
		executorService.execute(new Runnable() {
			
			public void run() {
				useCase.conditionSignal();
			}
		});
	}

	public void conditionWait() {
		lock.lock();
		try {
			System.out.println(Thread.currentThread().getName() + "拿到锁了");
			System.out.println(Thread.currentThread().getName() + "等待信号");
			condition.await();
			System.out.println(Thread.currentThread().getName() + "拿到信号");
		} catch (Exception e) {

		} finally {
			System.out.println(Thread.currentThread().getName() + "释放锁了");
			lock.unlock();
		}
	}

	public void conditionSignal() {
		lock.lock();
		try {
			Thread.sleep(5000);
			System.out.println(Thread.currentThread().getName() + "拿到锁了");
			condition.signal();
			System.out.println(Thread.currentThread().getName() + "发出信号");
		} catch (Exception e) {

		} finally {
			System.out.println(Thread.currentThread().getName() + "释放锁了");
			lock.unlock();
		}
	}

}
