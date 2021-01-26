package com.kyumin.calendar.repository;

import org.springframework.stereotype.Repository;

import com.kyumin.calendar.domain.CalendarDTO;

@Repository
public class MemoryRepository implements CalendarRepository {

	@Override
	public void insertCalendar(CalendarDTO dto) {
		// TODO Auto-generated method stub
		
		System.out.println("memory : " + dto.toString());
	}
}
