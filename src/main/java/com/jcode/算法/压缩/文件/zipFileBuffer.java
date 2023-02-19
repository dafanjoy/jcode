package com.jcode.算法.压缩.文件;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class zipFileBuffer {
	
	
	private static String ZIP_FILE="D:/gitworkspace/zip/test.zip";
	 
	private static String JPG_FILE_PATH="D:/gitworkspace/zip/";
	
	private static String JPG_FILE_NAME="img";
 
	public static void zipFileBuffer() {
	    File zipFile = new File(ZIP_FILE);
	    try (ZipOutputStream zipOut = new ZipOutputStream(new FileOutputStream(zipFile));
	            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(zipOut)) {
	        //开始时间
	        long beginTime = System.currentTimeMillis();
	        for (int i = 1; i <4; i++) {
	            try (BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(JPG_FILE_PATH + i + ".jpg"))) {
	                zipOut.putNextEntry(new ZipEntry(i + ".jpg"));
	                int len = 0;
	                byte[] data = new byte[8192];
	                while ((len = bufferedInputStream.read(data)) != -1) {
	                   bufferedOutputStream.write(data, 0, len);
	                }
	                
	                bufferedInputStream.close();
                    bufferedOutputStream.flush();
	            }
	        }
	        printInfo(beginTime);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
 
	
	public static void printInfo(long beginTime) {
		long endTime= System.currentTimeMillis();
		long total=endTime-beginTime;
		System.out.println("耗時："+total);
	}
	
	public static void main(String[] args) {
		zipFileBuffer();
	}

}
