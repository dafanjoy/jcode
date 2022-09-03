package com.jcode.design.静态内部类内部类;

public class StaticInnerTest {
	public StaticInnerTest() {
        System.out.println("静态内部类无参构造函数");
    }

    public static StaticInnerTest getInstance() {
        return Inner.sit;
    }
    //方法不需要设置同步
    public static class Inner{
        private static final StaticInnerTest sit = new StaticInnerTest();
    }
    private void play() {
    	System.err.println("自己玩");
    }
    
    private static void foot() {
    	System.err.println("自己吃");
    }

    public static void main(String[] args) {
        StaticInnerTest s1 = StaticInnerTest.getInstance();
        StaticInnerTest s2 = StaticInnerTest.getInstance();
        System.out.println(s1==s2);
        s1.play();
        s1.foot();
    }
}
