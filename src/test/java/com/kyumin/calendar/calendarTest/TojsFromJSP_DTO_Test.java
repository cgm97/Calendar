package com.kyumin.calendar.calendarTest;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import com.kyumin.calendar.domain.CalendarDTO;

public class TojsFromJSP_DTO_Test {
	
	@Test
	@DisplayName("DTO 테스트")
	void aaa() {
		CalendarDTO dto = new CalendarDTO();
		dto.setYear("2021");
		String a = dto.getYear();
		Assertions.assertEquals("2021", a);
	}
}
