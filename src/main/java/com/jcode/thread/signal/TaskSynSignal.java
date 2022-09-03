package com.jcode.thread.signal;

import java.util.Date;
import java.util.LinkedList;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 通过全局变量来控制线程
 * 
 * @author ThinkPad
 *
 */
public class TaskSynSignal {

	private LinkedList<Object> list = new LinkedList<>();// 自定义阻塞队列
	private static ArrayBlockingQueue<Object> arrayAueue;// 封装一个阻塞队列
	private static Date commitTime;
	private static int maxQueueSize;
	public static AtomicBoolean exits = new AtomicBoolean(false);// 判断是否已下发任务

    public static AtomicInteger count = new AtomicInteger(0);

	
	public static Object signal = new Object();// 控制任务请求线程信号量

	// 初始化变量
	public static synchronized void initQueue(int maxSize) {
		if (arrayAueue == null) {
			arrayAueue = new ArrayBlockingQueue<Object>(maxSize);
			maxQueueSize = maxSize;
			
		}

		if (commitTime == null) {
			commitTime = new Date();
		}
	}

	/**
	 * 阻塞队列插入：一旦插入说明阻塞队列中有值，这是唤醒其他线程过来取
	 * 
	 * @parame
	 */
	public void push(Object val) {
		synchronized (list) {
			list.add(val);
			list.notifyAll();
		}
	}

	/**
	* 如果链表中没有值，则所有线程进入到等待状态，知道有线程插入为止
	*@return
	*/
	public Object poll(){
		synchronized(list){
			while(list.size()==0){
				try{
					list.wait();
				}catch(InterruptedException e) {
					e.printStackTrace();
				}
			}
			Object val =list.remove();
			return val;
		}
	}

	public static void put(Object val) {
		try {
			arrayAueue.put(val);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static Object take() {
		Object val = null;
		try {
			val = arrayAueue.take();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return val;

	}

	public static void clear() {
		arrayAueue.clear();
	}

	public static int getTimeDiff() {
		long timeCommit = commitTime.getTime();
		long now = new Date().getTime();
		int minutes = (int) ((timeCommit - now) / (1000 * 60));

		return minutes;

	}

	public static int getSize() {

		return arrayAueue.size();

	}

	public static boolean judgeQueue() {
		try {
			if (arrayAueue.size() >= maxQueueSize || getTimeDiff() > 10) {

				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}

	}

	/**
	 * 所在线程挂起
	 */
	public static void signalWait() {
		synchronized (signal) {
			try {
				signal.wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	/**
	 * 所在线程恢复
	 */
	public static void signalNotify() {
		synchronized (signal) {
			signal.notifyAll();
		}
	}

}
