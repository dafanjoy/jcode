package com.jcode.design.event.base.model;
/**
 * ClassName: ConcreteSubject  
 * (具体主题角色类)
 * @author zhangtian  
 * @version
 */
public class ConcreteSubject extends Subject {
	private String state ;
	
	public String getState() {
		return state;
	}
	
	public void change(String newState) {
		this.state = newState ;
		System.out.println("主题状态为：" + state);
        //状态发生改变，通知各个观察者
        this.notifyObservers(state);
	}
}
