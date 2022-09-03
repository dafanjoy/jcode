package com.jcode.set;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class HashSetApp {
	public static void main(String[] args) {
		List list = new LinkedList() {
			{
				this.add("1");
				this.add("2");
				this.add("3");
			}
			
		};
		
		List list1 = new LinkedList(list) ;
		
		System.out.println(list1.size());
	}

}
