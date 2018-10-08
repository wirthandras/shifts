package hu.wirthandras.shifts.services;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class MonthService {

	public List<String> getDaysInCurrentMonth() {	
		List<String> list = new ArrayList<String>();
		for(int i = 0; i < getCurrentMonthDays(); i++) {
			list.add((i + 1) + "");
		}		
		return list;
	}
	
	private int getCurrentMonthDays() {
		LocalDate date = LocalDate.now();
		boolean leapYear = date.isLeapYear();
		int daysInMonth = date.getMonth().length(leapYear);
		return daysInMonth;
	}
	
	public Month getMonthName() {
		LocalDate date = LocalDate.now();
		return date.getMonth();
	}

	
}
