package com.example.taiwancityservice.util;

import com.example.taiwancityservice.enums.ReturnCode;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

public class DateUtil {

	public static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd").withLocale(Locale.TAIWAN)
			.withZone(ZoneId.systemDefault());

	public static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
			.withLocale(Locale.TAIWAN).withZone(ZoneId.systemDefault());
	
	public static final DateTimeFormatter dateTimeFormatter2 = DateTimeFormatter.ofPattern("yyyyMMddHHmmss")
			.withLocale(Locale.TAIWAN).withZone(ZoneId.systemDefault());

	public static String formatDate(LocalDateTime dateTime) {
		return dateFormatter.format(dateTime);
	}

	public static String formatDateTime(DateTimeFormatter dateTimeFormatter, LocalDateTime dateTime) {
		return dateTimeFormatter.format(dateTime);
	}
	
//	public static String dateTimeFormat(Date date) {
//
//		if (date == null) {
//			return null;
//		}
//
//		try {
//			return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
//		} catch (Exception ex) {
//			throw new ECIPException(ReturnCode.UNKNOWN_ERROR);
//		}
//	}
//
//	public static String dateFormat(Date date) {
//
//		if (date == null) {
//			return null;
//		}
//
//		try {
//			return new SimpleDateFormat("yyyy-MM-dd").format(date);
//		} catch (Exception ex) {
//			throw new ECIPException(ReturnCode.UNKNOWN_ERROR);
//		}
//	}

}
