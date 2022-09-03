package com.jcode.time;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class TimeApp {
	public static void main(String[] args) {
		SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		SimpleDateFormat dt1 = new SimpleDateFormat("2019-05-28 12:00:00");
		try {
			System.out.println(DateUtils.getDiffMinutes(dt.parse("2019-05-28 12:00:00").getTime(),dt.parse("2019-05-28 12:01:00").getTime()));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
