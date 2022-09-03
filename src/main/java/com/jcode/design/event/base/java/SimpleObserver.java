package com.jcode.design.event.base.java;

import java.util.Observable;
import java.util.Observer;

// 观察者类
public class SimpleObserver implements Observer {

	public SimpleObserver(SimpleObservable simpleObservable) {
		simpleObservable.addObserver(this);
	}
	
	// data为任意对象，用于传递参数  

	public void update(Observable observable, Object data) {
		System.out.println("Data has changed to " + ((SimpleObservable)observable).getData());   
	}
}
