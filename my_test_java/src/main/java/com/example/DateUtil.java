/**
 * Copyright (C) 2015 The Android_WorkBox Project
 */
package com.example;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DateUtil {

	private static final String[] dayofWeek = new String[]{"周日", "周一", "周二", "周三", "周四", "周五",
			"周六"};
	// private static final String[] monthOfYear = new
	// String[]{"一月","二月","三月","四月","五月","六月","七月","八月","九月","十月","十一月","十二月"};
	private static SimpleDateFormat mDayFmt = new SimpleDateFormat("dd日", Locale.getDefault());
	private static SimpleDateFormat mDateFmt = new SimpleDateFormat("MM月dd日", Locale.getDefault());
	private static SimpleDateFormat mHourFmt = new SimpleDateFormat("HH:mm", Locale.getDefault());
	private static SimpleDateFormat mYearMonthDayFmt = new SimpleDateFormat("yyyy年MM月dd日");

	public static String getYearMonthDay(long time) {
		return mYearMonthDayFmt.format(new Date(time * 1000l));
	}

	public static String getTimeString(long time, long nowTime) {
		Calendar nowCalendar = Calendar.getInstance();
		nowCalendar.setTime(new Date(nowTime * 1000l));

		Date date = new Date(time * 1000l);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);

		StringBuffer buffer = new StringBuffer();
		if (nowTime > 0 && isToday(nowCalendar, calendar)) {
			buffer.append("今天 ");
			buffer.append(mHourFmt.format(date));
		} else if (nowTime > 0 && isNextDay(nowCalendar, calendar)) {
			buffer.append("明天 ");
			buffer.append(mHourFmt.format(date));
		} else if (nowTime > 0 && isLastDay(nowCalendar, calendar)) {
			buffer.append("昨天 ");
			buffer.append(mHourFmt.format(date));
		} else {
			buffer.append(mDateFmt.format(date));
			buffer.append(" ");
			try {
				buffer.append(dayofWeek[calendar.get(Calendar.DAY_OF_WEEK) - 1]);
			} catch (Exception e) {
			}
			buffer.append(" ");
			buffer.append(mHourFmt.format(date));
		}
		return buffer.toString();
	}

	public static String getTimeWithoutMonthString(long time, long nowTime) {
		Calendar nowCalendar = Calendar.getInstance();
		nowCalendar.setTime(new Date(nowTime * 1000l));

		Date date = new Date(time * 1000l);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);

		StringBuffer buffer = new StringBuffer();
		if (nowTime > 0 && isToday(nowCalendar, calendar)) {
			buffer.append("今天 ");
			buffer.append(mHourFmt.format(date));
		} else if (nowTime > 0 && isNextDay(nowCalendar, calendar)) {
			buffer.append("明天 ");
			buffer.append(mHourFmt.format(date));
		} else if (nowTime > 0 && isLastDay(nowCalendar, calendar)) {
			buffer.append("昨天 ");
			buffer.append(mHourFmt.format(date));
		} else {
			buffer.append(mDayFmt.format(date));
			buffer.append(" ");
			try {
				buffer.append(dayofWeek[calendar.get(Calendar.DAY_OF_WEEK) - 1]);
			} catch (Exception e) {
			}
			buffer.append(" ");
			buffer.append(mHourFmt.format(date));
		}
		return buffer.toString();
	}

	/**
	 *
	 * @param time 这个time是毫秒数
	 * @param nowTime 这个nowtime是h毫秒数
	 * @return
     */
	public static String getTimeStringSAS(long time, long nowTime) {
		Calendar nowCalendar = Calendar.getInstance();
		nowCalendar.setTime(new Date(nowTime));

		Date date = new Date(time);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);

		StringBuffer buffer = new StringBuffer();
		if (nowTime > 0 && isToday(nowCalendar, calendar)) {
			buffer.append("今天 ");
		} else if (nowTime > 0 && isLastDay(nowCalendar, calendar)) {
			buffer.append("昨天 ");
		} else {
			buffer.append(mDateFmt.format(date));
		}
		return buffer.toString();
	}

	public static String getMonthDayTime(long milliseconds) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(milliseconds);
		StringBuffer buffer = new StringBuffer();
		buffer.append((calendar.get(Calendar.MONTH) + 1) + "月")
				.append(calendar.get(Calendar.DAY_OF_MONTH) + "日   ");
		int hour = calendar.get(Calendar.HOUR_OF_DAY);
		buffer.append(hour < 10 ? "0" : "").append(hour + ":");
		int minute = calendar.get(Calendar.MINUTE);
		buffer.append(minute < 10 ? "0" : "").append(minute);
		return buffer.toString();
	}

	public static String getTime(long time) {
		return mHourFmt.format(new Date(time * 1000l));
	}

	public static String getWeek(long time) {
		Date date = new Date(time * 1000l);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		try {
			return dayofWeek[calendar.get(Calendar.DAY_OF_WEEK) - 1];
		} catch (Exception e) {
		}
		return null;
	}

	public static String getDay(long time) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("MM-dd", Locale.getDefault());
			return sdf.format(new Date(time * 1000l));
		} catch (Exception e) {
		}
		return "未知";
	}

	/**
	 * 获得月份
	 *
	 * @param time
	 * @return
	 */
	public static String getMonth(long time) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("MM月", Locale.getDefault());
			return sdf.format(new Date(time * 1000l));
			// Calendar nowCalendar = Calendar.getInstance();
			// nowCalendar.setTime(new Date(time * 1000l));
			// int month = nowCalendar.get(Calendar.MONTH);
			// return monthOfYear[month];
		} catch (Exception e) {
		}
		return "未知";
	}

	private static boolean isNextDay(Calendar now, Calendar time) {
		Calendar nowCalendar = Calendar.getInstance();
		nowCalendar.setTime(now.getTime());
		nowCalendar.add(Calendar.DAY_OF_MONTH, 1);
		return nowCalendar.get(Calendar.DAY_OF_MONTH) == time.get(Calendar.DAY_OF_MONTH)
				&& now.get(Calendar.MONTH) == time.get(Calendar.MONTH)
				&& nowCalendar.get(Calendar.YEAR) == time.get(Calendar.YEAR);
	}

	private static boolean isLastDay(Calendar now, Calendar time) {
		Calendar nowCalendar = Calendar.getInstance();
		nowCalendar.setTime(now.getTime());
		nowCalendar.add(Calendar.DAY_OF_MONTH, -1);
		return nowCalendar.get(Calendar.DAY_OF_MONTH) == time.get(Calendar.DAY_OF_MONTH)
				&& nowCalendar.get(Calendar.MONTH) == time.get(Calendar.MONTH)
				&& nowCalendar.get(Calendar.YEAR) == time.get(Calendar.YEAR);
	}

	private static boolean isToday(Calendar now, Calendar time) {
		return now.get(Calendar.DAY_OF_MONTH) == time.get(Calendar.DAY_OF_MONTH)
				&& now.get(Calendar.MONTH) == time.get(Calendar.MONTH)
				&& now.get(Calendar.YEAR) == time.get(Calendar.YEAR);
	}

	public static String getHomeworkTitleDay(long time, long nowTime) {
		Calendar nowCalendar = Calendar.getInstance();
		nowCalendar.setTime(new Date(nowTime * 1000l));

		Date date = new Date(time * 1000l);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		StringBuffer buffer = new StringBuffer();
		if (nowTime > 0 && isToday(nowCalendar, calendar)) {
			buffer.append("今天 ");
		} else if (nowTime > 0 && isNextDay(nowCalendar, calendar)) {
			buffer.append("明天 ");
		} else if (nowTime > 0 && isLastDay(nowCalendar, calendar)) {
			buffer.append("昨天 ");
		} else {
			try {
				buffer.append(dayofWeek[calendar.get(Calendar.DAY_OF_WEEK) - 1]);
			} catch (Exception e) {
			}
			buffer.append(" ");
			buffer.append(mDateFmt.format(date));
			buffer.append(" ");
		}
		return buffer.toString();

	}

	public static String formatCostTime(int time) {
		if (time <= 0)
			return "00'00\"00";
		else {
			int nonosecond = (time % 1000);
			int minute = time / 1000 / 60;
			int second = time / 1000 - minute * 60;
			return unitFormat(minute) + "'" + unitFormat(second) + "\""
					+ unitFormat(nonosecond / 10);
		}
	}

	public static String formatCostTime1(int time) {
		String timeStr = null;
		int minute = 0;
		int second = 0;
		if (time <= 0)
			return "00:00";
		else {
			minute = time / 60;
			second = time % 60;
			timeStr = unitFormat(minute) + ":" + unitFormat(second);
		}
		return timeStr;
	}


	public static String formatCostTime2(int time) {
		if (time <= 0)
			return "";
		else {
			int minute = time / 60;
			int second = time - minute * 60;
			if (second == 0) {
				return minute + "分钟";
			} else {
				return minute + "分" + unitFormat(second) + "秒";
			}
		}
	}

	/**
	 * 将秒 -> 00:00:00
	 * @update wutong
	 * @param time 总的秒数
	 * @return
	 */
	public static String secToTime(int time) {
		String timeStr = null;
		int hour = 0;
		int minute = 0;
		int second = 0;
		if (time <= 0) {
			return "00:00:00";
		} else {
			minute = time / 60;
			if (minute < 60) {
				second = time % 60;
				timeStr = unitFormat(hour) + ":" + unitFormat(minute) + ":" + unitFormat(second);
			} else {
				hour = minute / 60;
				if (hour > 99) {
					return "99:59:59";
				}
				minute = minute % 60;
				second = time - hour * 3600 - minute * 60;
				timeStr = unitFormat(hour) + ":" + unitFormat(minute) + ":" + unitFormat(second);
			}
		}
		return timeStr;
	}

	public static String unitFormat(int i) {
		String retStr = null;
		if (i >= 0 && i < 10)
			retStr = "0" + Integer.toString(i);
		else
			retStr = "" + i;
		return retStr;
	}

	/**
	 * 获得倒计时
	 *
	 * @param time
	 * @param nowTime
	 * @return
	 */
	public static int[] getCountDownDays(int time, int nowTime) {
		int days = (time - nowTime) / 3600 / 24;
		int hours = (time - nowTime - days * 3600 * 24) / 3600;
		int minutes = (time - nowTime - days * 3600 * 24 - hours * 3600) / 60;
		int second = (time - nowTime) % 60;
		return new int[]{days, hours, minutes, second};
	}

}
