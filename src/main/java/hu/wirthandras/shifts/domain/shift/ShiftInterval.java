package hu.wirthandras.shifts.domain.shift;

import javax.validation.constraints.NotNull;

public class ShiftInterval {

	@NotNull
	private int from = 6;
	@NotNull
	private int to = 18;
	
	public ShiftInterval() {
		this.from = 6;
		this.to = 18;
	}
	
	public ShiftInterval(int from, int to) {
		super();
		this.from = from;
		this.to = to;
	}

	public int getFrom() {
		return from;
	}

	public void setFrom(int from) {
		this.from = from;
	}

	public int getTo() {
		return to;
	}

	public void setTo(int to) {
		this.to = to;
	}
	
}
