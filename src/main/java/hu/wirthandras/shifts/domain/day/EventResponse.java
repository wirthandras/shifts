package hu.wirthandras.shifts.domain.day;

import java.util.List;

public class EventResponse {

	private List<EventLocalized> result;

	public EventResponse() {
		super();
	}

	public EventResponse(List<EventLocalized> result) {
		super();
		this.result = result;
	}

	public List<EventLocalized> getResult() {
		return result;
	}

	public void setResult(List<EventLocalized> result) {
		this.result = result;
	}

}
