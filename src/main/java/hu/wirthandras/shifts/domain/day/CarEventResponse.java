package hu.wirthandras.shifts.domain.day;

import java.util.List;

import hu.wirthandras.shifts.domain.car.CarEvent;

public class CarEventResponse {

	private List<CarEvent> result;
	
	public CarEventResponse() {
		super();
	}
	
	public CarEventResponse(List<CarEvent> result) {
		super();
		this.result = result;
	}

	public List<CarEvent> getResult() {
		return result;
	}

	public void setResult(List<CarEvent> result) {
		this.result = result;
	}

}
