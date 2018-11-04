package hu.wirthandras.shifts.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import hu.wirthandras.shifts.domain.car.Car;

@Entity
public class Shift {
	
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Id
	protected Long id;
	protected Date date;
	protected int start;
	protected int end;
	@ManyToOne(optional=false)
	protected Car car;
	
	public Shift(Date date, int start, int end, Car car) {
		super();
		this.date = date;
		this.start = start;
		this.end = end;
		this.car = car;
	}

	public Shift() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getEnd() {
		return end;
	}

	public void setEnd(int end) {
		this.end = end;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + end;
		result = prime * result + start;
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
		Shift other = (Shift) obj;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (end != other.end)
			return false;
		if (start != other.start)
			return false;
		return true;
	}
	
	public Object[] toObjectArray() {
		return new Object[] {date, start, end};
	}

}
