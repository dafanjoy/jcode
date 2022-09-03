package com.jcode.future;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class CustomFuture<T>  {
	

	protected T result;
	protected AtomicBoolean done = new AtomicBoolean(false);
	protected AtomicBoolean sucess = new AtomicBoolean(false);
	protected Condition condition = new ReentrantLock().newCondition();
	protected Throwable cause;

	protected List<CustomFutureListener> listeners = new ArrayList<CustomFutureListener>();
	
	public CustomFuture() {
		
	}
	
	public void addListener(CustomFutureListener listener) {
		if(listener == null) {
			
		}
		//
	}
	
	private T getResult() {
		if(isDone()) {
			try {
				condition.await();
			} catch (InterruptedException ex) {
				// TODO Auto-generated catch block
				throw new RuntimeException(ex);
			}
		}
		
		if(cause!=null) {
			throw new RuntimeException(cause);
		}
		
		return this.result;
		
		
	}
	

	public void setResult(T result) {
		this.result = result;
	}
	
	public boolean isDone() {
		return done.get();
	}

	public AtomicBoolean getDone() {
		return done;
	}

	public void setDone(AtomicBoolean done) {
		this.done = done;
	}

	public AtomicBoolean getSucess() {
		return sucess;
	}

	public void setSucess(AtomicBoolean sucess) {
		this.sucess = sucess;
	}

	
	

    
}
