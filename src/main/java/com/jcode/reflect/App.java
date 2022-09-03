package com.jcode.reflect;

import java.lang.reflect.Method;

public class App {


	  public void testPrint(String params,String params1,int a){
		  System.out.println(params);
		  System.out.println(params1);
		  System.out.println(a);
	  }
	  
	  public static void main(String[] args) {
	    try {
	      App userTest=new App();
	      Class<?> clazz=userTest.getClass();
	     //Class<?> handlerClass = Class.forName("");
	      Method[] methods=clazz.getDeclaredMethods();
	      for (Method method : methods) {
	        if(!"testPrint".equals(method.getName()))
	          continue;
	        System.out.println(method.getName());
	        String[] arr=new String[]{"aaaa",null,"bbbb"};
	        //正确
	        method.invoke(clazz.newInstance(),new Object[]{"aaa","bbb",1});
	      }
	    } catch (Exception e) {
	      e.printStackTrace();
	    }
	  }
}
