package hu.wirthandras.shifts.domain.employee;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import hu.wirthandras.shifts.domain.job.Job;

@Entity
public class Employee implements Comparable<Employee> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	@OneToOne
	private Job job;

	private boolean night;

	private int monthlyHours;

	@OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
	private Set<EmployeeEvent> events;

	public Employee() {

	}

	public Employee(String name, Job job) {
		super();
		this.name = name;
		this.job = job;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Job getJob() {
		return job;
	}

	public void setJob(Job job) {
		this.job = job;
	}

	public boolean isNight() {
		return night;
	}

	public void setNight(boolean night) {
		this.night = night;
	}

	public int getMonthlyHours() {
		return monthlyHours;
	}

	public void setMonthlyHours(int monthlyHours) {
		this.monthlyHours = monthlyHours;
	}

	@Override
	public int compareTo(Employee o) {
		return this.name.compareTo(o.name);
	}

}
