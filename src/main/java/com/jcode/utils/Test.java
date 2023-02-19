package com.jcode.utils;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		byte[] a = new byte[2];
		a[0]=(byte) 0xFA;
	    a[1]=(byte) 0x2F;
	    
		System.err.println(BytesUtils.toInt16(a)&0xffff);
	}

}
