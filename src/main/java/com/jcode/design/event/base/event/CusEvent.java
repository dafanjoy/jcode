package com.jcode.design.event.base.event;

import java.util.EventObject;
/**
 * ClassName: CusEvent  
 * (事件类,用于封装事件源及一些与事件相关的参数)
 * @author zhangtian  
 * @version
 */
public class CusEvent extends EventObject {

	private static final long serialVersionUID = 1L;
	// 事件源 
	private Object source;
	
	public CusEvent(Object source) {
		super(source);
		this.source = source ;
	}

	public Object getSource() {
		return source;
	}

	public void setSource(Object source) {
		this.source = source;
	}
}
