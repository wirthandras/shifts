package hu.wirthandras.shifts.domain.shift;

import javax.validation.constraints.NotNull;

import hu.wirthandras.shifts.domain.car.type.CarType;

public class ShiftInterval implements Comparable<ShiftInterval> {

	private static final int HOUR24 = 24;

	private int dayId;
	@NotNull
	private int from = 6;
	@NotNull
	private int to = 18;
	private CarType carType;

	public ShiftInterval() {
		this.from = 6;
		this.to = 18;
	}

	public ShiftInterval(@NotNull int from, @NotNull int to, CarType carType) {
		super();
		this.from = from;
		this.to = to;
		this.carType = carType;
	}

	public int getDayId() {
		return dayId;
	}

	public void setDayId(int dayId) {
		this.dayId = dayId;
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

	public CarType getCarType() {
		return carType;
	}

	public void setCarType(CarType carType) {
		this.carType = carType;
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
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((carType == null) ? 0 : carType.hashCode());
		result = prime * result + dayId;
		result = prime * result + from;
		result = prime * result + to;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ShiftInterval other = (ShiftInterval) obj;
		if (carType == null) {
			if (other.carType != null)
				return false;
		} else if (!carType.equals(other.carType))
			return false;
		if (dayId != other.dayId)
			return false;
		if (from != other.from)
			return false;
		if (to != other.to)
			return false;
		return true;
	}

}
