package com.jcode.thread.jvolatile;

public class ThreadApp extends Thread {

	private String i ="sss";

    private void setI(String i){
        this.i=i;
    }
    @Override
    public void run() {
        System.out.println("进入方法"+i);
        while (i.equals("sss")){
//            System.out.println("方法执行");
//
//            try {
//                Thread.sleep(3000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
        }
        System.out.println("线程结束");
    }

    public static void main(String[] args ) throws InterruptedException{
    	ThreadApp test=new ThreadApp();
        test.start();
        Thread.sleep(1000);
        System.out.println("线程设置了stop");
        test.setI("线程设置了stop");
    }



}
