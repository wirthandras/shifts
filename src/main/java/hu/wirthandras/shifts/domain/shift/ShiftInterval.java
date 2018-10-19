package hu.wirthandras.shifts.domain.shift;

import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Component;

import hu.wirthandras.shifts.domain.car.Car;

@Component
public class ShiftInterval {

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
	
}
