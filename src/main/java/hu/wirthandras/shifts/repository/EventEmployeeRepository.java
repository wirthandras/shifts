package hu.wirthandras.shifts.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import hu.wirthandras.shifts.domain.employee.Employee;
import hu.wirthandras.shifts.domain.employee.EmployeeEvent;

@Repository
public interface EventEmployeeRepository extends CrudRepository<EmployeeEvent, Long> {

	public List<EmployeeEvent> findAll();

	public List<EmployeeEvent> findByEmployeeAndDate(Employee employee, LocalDate date);

}
