package com.jcode.design.event.base.model;

import java.util.ArrayList;
import java.util.List;

/**
 * ClassName: Subject  
 * (用来保存注册的观察者对象)
 * @author zhangtian  
 * @version
 */
public abstract class Subject {
	// 用来保存注册的观察者对象
	private List<Observer> list = new ArrayList<Observer>(100) ;
	
	// 注册观察者对象
	public void attach(Observer observer) {
		list.add(observer) ;
		System.out.println("Attach an observer...");
	}
	
	// 删除观察者对象
	public void detach(Observer observer) {
		list.remove(observer) ;
	}
	
	// 通知所有注册的观察者对象
	public void notifyObservers(String newState) {
		for(Observer observer : list) {
			observer.update(newState) ;
		}
	}
	
}
