package com.jcode.dll;

import org.xvolks.jnative.JNative;

public class DllApp {
	public static void main(String[] args) {
		String data = "39C90467";
		StringBuilder resdata= new StringBuilder();
		CallPswLibrary.instance.gen_digest(data, data.length(), resdata);
		//CallPswLibrary.instance.hello(1, 2);
		//System.out.println(CallPswLibrary.instance.hello(1, 2));
		
		// JNative jnt = new JNative("TransferEth.dll", "Transfer_Ethernet")
	}
}
