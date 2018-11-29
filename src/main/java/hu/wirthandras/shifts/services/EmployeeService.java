package hu.wirthandras.shifts.services;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hu.wirthandras.shifts.domain.employee.Employee;
import hu.wirthandras.shifts.domain.employee.Job;
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

	public Set<Employee> filter(Job job) {
		return getEmployees().stream()
		.filter(x -> x.getJob().equals(job))
		.collect(Collectors.toSet());
	}

}
