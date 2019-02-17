package hu.wirthandras.shifts.services;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hu.wirthandras.shifts.domain.employee.Employee;
import hu.wirthandras.shifts.domain.job.Job;
import hu.wirthandras.shifts.exception.EmployeeNotFoundException;
import hu.wirthandras.shifts.repository.EmployeeRepository;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepository repositoryEmployee;

	@Autowired
	private JobRepository repositoryJob;

	public Employee getEmployee(String idAsString) throws EmployeeNotFoundException {
		long id = Long.parseLong(idAsString);
		Optional<Employee> op = repositoryEmployee.findById(id);
		if (op.isPresent()) {
			return op.get();
		}
		throw new EmployeeNotFoundException();
	}

	public List<Employee> getEmployees() {
		return repositoryEmployee.findAll();
	}

	public void save(Employee e) {
		repositoryEmployee.save(e);
	}

	public Set<Employee> filter(String jobName) {
		return getEmployees().stream()
		.filter(x -> x.getJob().getName().equals(jobName))
		.collect(Collectors.toSet());
	}

	public List<Job> getJobs() {
		return repositoryJob.findAll();
	}

}
