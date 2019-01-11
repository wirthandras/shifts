package hu.wirthandras.shifts.domain;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class MontlyWorkingHours {

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private Long id;
	private LocalDate month;
	private int montlyWorkingHour;

	public MontlyWorkingHours() {
		super();
	}

	public LocalDate getMonth() {
		return month;
	}

	public void setMonth(LocalDate month) {
		this.month = month;
	}

	public int getMontlyWorkingHour() {
		return montlyWorkingHour;
	}

	public void setMontlyWorkingHour(int montlyWorkingHour) {
		this.montlyWorkingHour = montlyWorkingHour;
	}

}
