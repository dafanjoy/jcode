package com.jcode.design.event.base.event;

/**
 * ClassName: MainTest  
 * (事件驱动测试)
 * @author zhangtian  
 * @version
 */
public class MainTest {
	public static void main(String[] args) {
		EventSourceObject object = new EventSourceObject();  
        //注册监听器  
        object.addCusListener(new CusEventListener(){  
            @Override  
            public void fireCusEvent(CusEvent e) {  
                super.fireCusEvent(e);  
            }  
        });  
        
        //触发事件  
        object.setName("eric");  
	}
}
