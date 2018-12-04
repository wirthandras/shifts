package hu.wirthandras.shifts.domain.car;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import hu.wirthandras.shifts.domain.Event;

@Entity
@Table(uniqueConstraints={
	    @UniqueConstraint(columnNames = {"CAR_ID", "DATE", "TYPE"})
	})
public class CarEvent implements Event {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(optional = false)
	private Car car;

	private LocalDate date;

	@Enumerated(EnumType.STRING)
	private CarEventType type;

	public CarEvent() {
		super();
	}

	public CarEvent(Car car, LocalDate dayDate, CarEventType type) {
		this.car = car;
		this.date = dayDate;
		this.type = type;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Car getCar() {
		return car;
	}

	public void setCar(Car car) {
		this.car = car;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public CarEventType getType() {
		return type;
	}

	public void setType(CarEventType type) {
		this.type = type;
	}

	@Override
	public String getTypeString() {
		return getType().toString();
	}

}
