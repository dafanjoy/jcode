package com.jcode.future;

public interface CustomFutureListener {
	
	void completeEvent(CustomFuture<?> future) throws Exception;
}