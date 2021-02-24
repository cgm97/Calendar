package com.kyumin.calendar.calendarTest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

import org.junit.Test;

public class changeEndDateTest {
	
	private SimpleDateFormat change = new SimpleDateFormat("yyyy-MM-dd");
	private Calendar cal = Calendar.getInstance();
	
	@Test
	public void calendarDate() throws ParseException {
		Date before_endDate = change.parse("2021-02-23");
		
		cal.setTime(before_endDate);
		cal.add(Calendar.DATE, -1); //1 , -1
		
		String after_endDate = change.format(cal.getTime());
		System.out.println(after_endDate);
	}
	
	@Test
	public void localDate() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate ld = LocalDate.parse("2021-02-25");
		LocalDate ld2 = LocalDate.now();
		String result = ld.plusDays(1).format(formatter);
		ld2 = ld2.plusDays(-1);
		System.out.println("result : " + result);
		System.out.println("ld2 : " + ld2);
	}
}
