package hu.wirthandras.shifts.domain;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class MonthlyWorkingHours {

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private Long id;
	private LocalDate month;
	private int monthlyWorkingHour;

	public MonthlyWorkingHours() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDate getMonth() {
		return month;
	}

	public void setMonth(LocalDate month) {
		this.month = month;
	}

	public int getMonthlyWorkingHour() {
		return monthlyWorkingHour;
	}

	public void setMonthlyWorkingHour(int monthlyWorkingHour) {
		this.monthlyWorkingHour = monthlyWorkingHour;
	}

}
