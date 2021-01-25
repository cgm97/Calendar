package com.kyumin.calendar.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;

import com.kyumin.calendar.domain.CalendarDTO;

@Repository
public class MemoryRepository implements CalendarRepository {

	@Override
	public void insertCalendar(CalendarDTO dto) {
		// TODO Auto-generated method stub
		
		System.out.println("memory : " + dto.toString());
	}
}
