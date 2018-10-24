package hu.wirthandras.shifts.domain.shift;

import java.util.NavigableSet;
import java.util.TreeSet;

import javax.validation.constraints.NotNull;

import hu.wirthandras.shifts.domain.car.Car;

public class ShiftInterval implements Comparable<ShiftInterval> {

	private static final int ACCEPTABLE_BOUND_LIMIT = 1;
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
				if (car == null && s.getCar() == null || car.equals(s.getCar())) {
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
		int hashCode = this.from * HOUR24 + this.to;
		if (car != null) {
			hashCode += car.getPlateNumber().hashCode();
		}
		return hashCode;
	}

	public boolean isCarLocked(ShiftInterval interval) {
		if (interval.getCar().equals(car)) {
			return hasConflict(interval);
		}
		return false;
	}

	private boolean hasConflict(ShiftInterval other) {
		if (hasSameEndPoint(other)) {
			return true;
		}
		return checkBoundaries(other);
	}

	private boolean checkBoundaries(ShiftInterval other) {
		NavigableSet<Integer> set = new TreeSet<>();
		set.add(from);
		set.add(from + getDuration());

		int of = other.getFrom();
		int od = other.getDuration();

		return set.subSet(of, of + od).size() > ACCEPTABLE_BOUND_LIMIT;
	}

	private boolean hasSameEndPoint(ShiftInterval other) {
		return from == other.getFrom() || to == other.getTo();

	}

}
