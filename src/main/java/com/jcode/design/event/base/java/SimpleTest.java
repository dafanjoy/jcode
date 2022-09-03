package com.jcode.design.event.base.java;

public class SimpleTest {
	public static void main(String[] args) {
		SimpleObservable doc = new SimpleObservable ();    
		SimpleObserver view = new SimpleObserver (doc);    
		doc.setData(1);    
		doc.setData(2);    
		doc.setData(2);    
		doc.setData(3);  
	}
}
