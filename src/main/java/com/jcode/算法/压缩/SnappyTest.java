package com.jcode.算法.压缩;

import java.io.IOException;
import java.util.Base64;

import org.xerial.snappy.Snappy;

public class SnappyTest {
	
    public static byte[] compress(byte[] inBytes) throws IOException {
        return Snappy.compress(inBytes);
    }

    public static byte[]  uncompress(byte[] inBytes) throws IOException {
    	   return Snappy.uncompress(inBytes);
    }

}
