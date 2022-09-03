package com.jcode.dll;

import com.sun.jna.Library;
import com.sun.jna.Native;

public interface  CallPswLibrary extends Library {
	CallPswLibrary instance = (CallPswLibrary)Native.loadLibrary("E:\\eclipse-workspace\\netty-master\\hhxx-test\\lib\\p_en", CallPswLibrary.class);	
	public void gen_digest(String data,int len,StringBuilder resData);
	
	public int hello(int a,int b);
}
