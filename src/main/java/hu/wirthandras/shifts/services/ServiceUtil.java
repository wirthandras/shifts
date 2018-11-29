package hu.wirthandras.shifts.services;

import static java.time.temporal.TemporalAdjusters.firstDayOfNextMonth;

import java.time.LocalDate;

public class ServiceUtil {
	
	public static LocalDate resolveDateFromDayId(String dayId) {
		int dayNumber = Integer.parseInt(dayId);
		return resolveDateFromDayId(dayNumber);
	}
	
	public static LocalDate resolveDateFromDayId(int dayId) {
		return LocalDate.now().withDayOfMonth(dayId);
	}
	
	public static LocalDate resolveNextMonthDateFromDayId(int dayId) {
		LocalDate now = LocalDate.now();
		LocalDate nextMonth = now.with(firstDayOfNextMonth());
		return nextMonth.withDayOfMonth(dayId);
	}

}
