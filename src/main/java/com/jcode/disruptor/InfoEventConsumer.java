package com.jcode.disruptor;

import com.lmax.disruptor.WorkHandler;

public class InfoEventConsumer implements WorkHandler<InfoEvent> {
	private int cnt = 0;
	private long startTime;

	public InfoEventConsumer() {
		this.startTime = System.currentTimeMillis();
	}

	@Override
	public void onEvent(InfoEvent event) throws Exception {
		// TODO Auto-generated method stub
		cnt++;
		InfoEvent eventInfo = event;
		if (cnt >= 50000000) {
			cnt = 0;
			long endTime = System.currentTimeMillis();
			System.out.println(" 消耗时间:" + (endTime - startTime) + "毫秒");
			this.startTime = System.currentTimeMillis();

		}
	}
}
