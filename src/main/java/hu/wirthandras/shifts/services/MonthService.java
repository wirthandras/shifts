package hu.wirthandras.shifts.services;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import hu.wirthandras.shifts.domain.day.Day;

@Service
public class MonthService {

	public List<Day> getDaysInCurrentMonth() {
		List<Day> list = new ArrayList<>();
		for (int i = 0; i < getCurrentMonthDays(); i++) {
			list.add(new Day(i + 1));
		}
		return list;
	}

	public int getCurrentMonthDays() {
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
