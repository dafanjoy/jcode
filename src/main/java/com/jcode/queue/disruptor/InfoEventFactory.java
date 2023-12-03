package com.jcode.queue.disruptor;

import com.lmax.disruptor.EventFactory;

public class InfoEventFactory implements EventFactory<InfoEvent>{
	public InfoEvent newInstance() {
		return new InfoEvent();
	}
 
}
