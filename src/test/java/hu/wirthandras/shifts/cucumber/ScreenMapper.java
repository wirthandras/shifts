package hu.wirthandras.shifts.cucumber;

import java.util.HashMap;
import java.util.Map;

public class ScreenMapper {

	private Map<String, String> map;

	public ScreenMapper() {
		map = new HashMap<>();
		map.put("Home", "home");
		map.put("Generate", "generate");
		map.put("Employees", "employees");
		map.put("Cars", "cars");
		map.put("Shifts", "shifts");
		map.put("New Car", "newCar");
		map.put("New Employee", "newEmployee");
		map.put("Details", "details");
		map.put("New", "newsDropdown");
		map.put("Shiftplanner", "shiftplanner");
		map.put("Planning", "planning");
		map.put("Submit", "submit");
		map.put("Shift", "formShift");
		map.put("new shift element", "shiftElement");
		map.put("Error", "errorMessage");
		
		
		
	}
	
	public String getId(String screenName) {
		return map.get(screenName);
	}

}
