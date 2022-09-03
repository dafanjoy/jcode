package com.jcode.time;

import java.util.Date;

public class DateUtils {
	/**
	 *  返回两个时间的相差分，需要区分前后时间
	 * @param endDate
	 * @param nowDate
	 * @return
	 */
	public static long getDiffMinutes(Date endDate, Date nowDate) {

		long nd = 1000 * 24 * 60 * 60;
		long nh = 1000 * 60 * 60;
		long nm = 1000 * 60;
		long ns = 1000;
		long hs = 1000;
		// 获得两个时间的毫秒时间差异
		long diff = endDate.getTime() - nowDate.getTime();
		// 计算差多少天
		long day = diff / nd;
		// 计算差多少小时
		long hour = diff % nd / nh;
		// 计算差多少分钟
		long min = diff % nd % nh / nm;
		return min;
	}
	
	/**
	 * 返回两个时间的相差分钟的绝对值，所以不用区分前后时间
	 * @param time1
	 * @param time2
	 * @return
	 */
	public static final int getDiffMinutes(long time1, long time2) {
		return (int) ((time1 - time2) / (1000 * 60));
	}
}
