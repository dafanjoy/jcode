package com.jcode.dll;

public class JNATestDll {
	static {
		System.loadLibrary("psw_en");
	}

	public native static String gen_digest(String data,int len,String resData);
}
