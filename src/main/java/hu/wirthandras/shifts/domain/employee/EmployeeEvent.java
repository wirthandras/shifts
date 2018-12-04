package hu.wirthandras.shifts.domain.employee;

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
	    @UniqueConstraint(columnNames = {"EMPLOYEE_ID", "DATE", "TYPE"})
	})
public class EmployeeEvent implements Event {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(optional=false)
	private Employee employee;

	private LocalDate date;

	@Enumerated(EnumType.STRING)
	private EmployeeEventType type;

	public EmployeeEvent() {
		super();
	}

	public EmployeeEvent(Employee employee, LocalDate date, EmployeeEventType type) {
		super();
		this.employee = employee;
		this.date = date;
		this.type = type;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public EmployeeEventType getType() {
		return type;
	}

	public void setType(EmployeeEventType type) {
		this.type = type;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	@Override
	public String getTypeString() {
		return getType().toString();
	}

}
