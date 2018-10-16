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
	}
	
	public String getId(String screenName) {
		return map.get(screenName);
	}

}
