package com.jcode.算法.压缩.文件.channel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.WritableByteChannel;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZipFileChannel {
	
	private static String ZIP_FILE="D:/gitworkspace/zip/test.zip";
	 
	private static String JPG_FILE_PATH="D:/gitworkspace/zip/";
	
	private static String JPG_FILE_NAME="img";
	public static void zipFileChannel() {

	    //开始时间
	    long beginTime = System.currentTimeMillis();
	    File zipFile = new File(ZIP_FILE);
	    try (ZipOutputStream zipOut = new ZipOutputStream(new FileOutputStream(zipFile));
	        WritableByteChannel writableByteChannel = Channels.newChannel(zipOut)) {
	    	for (int i = 1; i <4; i++) {
	            try (FileChannel fileChannel = new FileInputStream(JPG_FILE_PATH + i + ".jpg").getChannel()) {
	                zipOut.putNextEntry(new ZipEntry(JPG_FILE_NAME + i + ".jpg"));
	                fileChannel.transferTo(0, fileChannel.size(), writableByteChannel);
	                //System.out.println("size:"+fileChannel.size());
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
		zipFileChannel();
	}
}
