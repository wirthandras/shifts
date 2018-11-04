package hu.wirthandras.shifts.domain;

import hu.wirthandras.shifts.domain.car.Car;

public class ShiftForDisplay extends Shift {

	private boolean wish;

	//FIXME why need to be public (other fields are protected and thymeleaf can be display)
	public Car car;

	public ShiftForDisplay(Shift s) {
		this.id = s.id;
		this.date = s.date;
		this.start = s.start;
		this.end = s.end;
		this.car = s.car;
	}

	public boolean isWish() {
		return wish;
	}

	public void setWish(boolean wish) {
		this.wish = wish;
	}	
	
}
