package hu.wirthandras.shifts.domain.shift;

import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Component;

import hu.wirthandras.shifts.domain.car.Car;

@Component
public class ShiftInterval implements Comparable<ShiftInterval> {

	private static final int HOUR24 = 24;
	@NotNull
	private int from = 6;
	@NotNull
	private int to = 18;
	private Car car;

	public ShiftInterval() {
		this.from = 6;
		this.to = 18;
	}

	public ShiftInterval(@NotNull int from, @NotNull int to, Car car) {
		super();
		this.from = from;
		this.to = to;
		this.car = car;
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

	public Car getCar() {
		return car;
	}

	public void setCar(Car car) {
		this.car = car;
	}

	@Override
	public int compareTo(ShiftInterval o) {
		if (this.from != o.getFrom()) {
			return Integer.compare(this.from, o.from);
		} else {
			return Integer.compare(this.getDuration(), o.getDuration());
		}
	}

	public int getDuration() {
		if (from == to) {
			return HOUR24;
		} else {
			if (from > to) {
				return from - to;
			} else {
				return to - from;
			}
		}
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof ShiftInterval) {
			ShiftInterval s = (ShiftInterval) obj;
			if (s.from == this.from && s.to == this.to) {
				if (car == null && s.getCar() == null || car.equals(s.getCar()) ) {
					return true;
				} else {
					return false;
				}
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		int hashCode = this.from * 24 + this.to;
		if (car != null) {
			hashCode += car.getPlateNumber().hashCode();
		}
		return hashCode;
	}

}
