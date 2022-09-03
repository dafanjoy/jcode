package com.jcode.queue.threadqueue;

import java.util.Date;
import java.util.concurrent.ArrayBlockingQueue;

public class SealSynArrayBlockQueue {
	private static ArrayBlockingQueue<Object> arrayAueue;
	private static Date commitTime;
	private static int maxQueueSize;

	/**
	 * 初始化
	 * 
	 * @param maxSize
	 */
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
	 * put元素
	 * 
	 * @param val
	 */
	public static void put(Object val) {
		try {
			arrayAueue.put(val);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * take元素
	 * 
	 * @param val
	 * @throws InterruptedException
	 */
	public static Object take() throws InterruptedException {

		return arrayAueue.take();

	}

	/**
	 * 清空队列
	 */
	public static void clear() {
		arrayAueue.clear();
	}

	/**
	 * 时间比对
	 * 
	 * @return
	 */
	public static int getTimeDiff() {
		long timeCommit = commitTime.getTime();
		long now = new Date().getTime();
		int minutes = (int) ((timeCommit - now) / (1000 * 60));

		return minutes;

	}

	/**
	 * 长度
	 * 
	 * @return
	 */
	public static int getSize() {

		return arrayAueue.size();

	}

	/**
	 * 从容量和时间维度判读
	 * 
	 * @return
	 */
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

}
