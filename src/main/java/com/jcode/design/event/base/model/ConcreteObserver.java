package com.jcode.design.event.base.model;
/**
 * ClassName: ConcreteObserver  
 * (具体观察者角色类)
 * @author zhangtian  
 * @version
 */
public class ConcreteObserver implements Observer {
	//观察者的状态
    private String observerState;

	public void update(String newState) {
		/**
		 * 更新观察者的状态，使其与目标的状态保持一致
		*/
       this.observerState = newState;
       System.out.println("状态为："+observerState);
	}
}
