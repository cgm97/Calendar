package com.kyumin.calendar.calendarTest;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.kyumin.calendar.service.CalendarServiceImpl;

public class BeanTest {
	
	@Test
	public void BenaTest1() {
		ApplicationContext ac = new AnnotationConfigApplicationContext();
		CalendarServiceImpl calendarServiceImpl = ac.getBean("calendarServiceImpl",CalendarServiceImpl.class);
		System.out.println(calendarServiceImpl);
	}
}
