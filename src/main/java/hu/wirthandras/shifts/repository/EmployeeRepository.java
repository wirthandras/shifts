package hu.wirthandras.shifts.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import hu.wirthandras.shifts.domain.Employee;

public interface EmployeeRepository extends CrudRepository<Employee, Long> {
	
	public List<Employee> findAll();

}
