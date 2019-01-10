package hu.wirthandras.shifts.domain;

public class ShiftForDisplay extends Shift {

	private boolean wish;

	public ShiftForDisplay(Shift s) {
		this.id = s.id;
		this.date = s.date;
		this.start = s.start;
		this.end = s.end;
		this.carType = s.carType;
	}

	public boolean isWish() {
		return wish;
	}

	public void setWish(boolean wish) {
		this.wish = wish;
	}	
	
}
