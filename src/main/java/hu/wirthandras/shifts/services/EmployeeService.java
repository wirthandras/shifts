package hu.wirthandras.shifts.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hu.wirthandras.shifts.domain.Employee;
import hu.wirthandras.shifts.repository.EmployeeRepository;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepository repository;

	public Employee getEmployee(String idAsString) {
		long id = Long.parseLong(idAsString);
		return repository.findById(id).get();
	}

}
