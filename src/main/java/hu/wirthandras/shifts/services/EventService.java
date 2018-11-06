package hu.wirthandras.shifts.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class EventService {
	
	//TODO save events to DB
	
	//TODO retrive events from DB
	public List<String> getCarEvents(String day) {
		List<String> events = new ArrayList<>();
		events.add("cara" + day);
		events.add("carb" + day);
		events.add("carc" + day);
		return events;
	}

	//TODO retrive events from DB
	public List<String> getEmployeeEvents(String day) {
		List<String> events = new ArrayList<>();
		events.add("empa" + day);
		events.add("empb" + day);
		events.add("empc" + day);
		return events;
	}

}
