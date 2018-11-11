package hu.wirthandras.shifts.domain.day;

import java.util.List;

import hu.wirthandras.shifts.domain.employee.EmployeeEvent;

public class EmployeeEventResponse {
	
    private List<EmployeeEvent> result;
    
    public EmployeeEventResponse() {
    	super();
    }
    
    public EmployeeEventResponse(List<EmployeeEvent> result) {
    	super();
    	this.result = result;
    }
    
	public List<EmployeeEvent> getResult() {
		return result;
	}
	
	public void setResult(List<EmployeeEvent> result) {
		this.result = result;
	}
	

}
