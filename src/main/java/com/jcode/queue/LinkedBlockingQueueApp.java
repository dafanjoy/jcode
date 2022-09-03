package com.jcode.queue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class LinkedBlockingQueueApp {
	public static void main(String[] args) throws InterruptedException {

		LinkedBlockingQueue<Object> arrayAueue = new LinkedBlockingQueue<Object>(100);
		LinkedBlockingQueue<Object> arrayAueue2 = new LinkedBlockingQueue<Object>(100);

		for (int i = 0; i < 100; i++) {
//			//队列未满时，返回true；队列满则抛出IllegalStateException(“Queue full”)异常 
//			arrayAueue.add(i + "");
//			//队列未满时，返回true；队列满时返回false。非阻塞立即返回。
//			arrayAueue.offer(i + "");
//			
//			//队列未满时，返回true；队列满时返回false。非阻塞立即返回。
//			arrayAueue.offer(i+"",10,TimeUnit.SECONDS);

			//队列未满时，直接插入没有返回值；队列满时会阻塞等待，一直等到队列未满时再插入。 
		    arrayAueue.put(i + "");
	
		}
		
		System.out.println("arrayAueue转移前:"+arrayAueue.size());
		arrayAueue.drainTo(arrayAueue2);// 把arrayAueue中的元素全部转移到arrayAueue2中
		System.out.println("arrayAueue转移后:"+arrayAueue.size());
		System.out.println("arrayAueue2:"+arrayAueue2.size());
		
		
		while (arrayAueue.size() > 0) {
//			//队列不为空时，返回队首值并移除；队列为空时抛出NoSuchElementException()异常
//			arrayAueue.remove();
//		    //队列不为空时返回队首值并移除；队列为空时返回null。非阻塞立即返回。  
//			arrayAueue.poll();
//			//设定等待的时间，如果在指定时间内队列还未孔则返回null，不为空则返回队首值  
//			arrayAueue.poll(10,TimeUnit.SECONDS);
			//队列不为空返回队首值并移除；当队列为空时会阻塞等待，一直等到队列不为空时再返回队首值。
			arrayAueue.take();
			System.out.println("arrayAueue:"+arrayAueue.poll());
		}

		while (arrayAueue2.size() > 0) {
			System.out.println("arrayAueue2:"+arrayAueue2.take());
		}



		

	}

}
