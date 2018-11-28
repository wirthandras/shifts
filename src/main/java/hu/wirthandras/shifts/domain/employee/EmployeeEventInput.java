package hu.wirthandras.shifts.domain.employee;

public class EmployeeEventInput {

	private String employeeId;
	private String dayId;
	private EmployeeEventType eventType;

	public String getEmployeeId() {
		return employeeId;
	}

	public String getDayId() {
		return dayId;
	}

	public EmployeeEventType getEventType() {
		return eventType;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public void setDayId(String dayId) {
		this.dayId = dayId;
	}

	public void setEventType(EmployeeEventType eventType) {
		this.eventType = eventType;
	}

}
