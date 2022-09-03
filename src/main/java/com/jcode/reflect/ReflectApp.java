package com.jcode.reflect;

import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class ReflectApp {
	  public static void main(String[] args) {
		  try {
			Set<Class<?>> list = ClassUtil.getClasses("com.jcode.design.event");
			System.out.println(list.size());
            //Class demo = Class.forName("com.jcode.reflect.Atest");
            //demo.isAnnotationPresent(annotationClass)
            //Method[] dd =  demo.getMethods();
            //System.out.println(dd.length);
	
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	  }
	  

}
