package hu.wirthandras.shifts.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hu.wirthandras.shifts.domain.Employee;
import hu.wirthandras.shifts.repository.EmployeeRepository;

@Service
public class EmployeeService {

	private EmployeeRepository repository;
	
	@Autowired
	public void setEmployeeRepository(EmployeeRepository repo) {
		this.repository = repo;
	}

	public Employee getEmployee(String idAsString) {
		long id = Long.parseLong(idAsString);
		return repository.findById(id).get();
	}

	public List<Employee> getEmployees() {
		return repository.findAll();
	}
	
	public void save(Employee e) {
		repository.save(e);
	}
	
	public List<String> getEvents(String day) {
		List<String> events = new ArrayList<>();
		events.add("empa" + day);
		events.add("empb" + day);
		events.add("empc" + day);
		return events;
	}

}
