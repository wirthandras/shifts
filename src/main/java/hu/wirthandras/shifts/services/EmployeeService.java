package hu.wirthandras.shifts.services;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hu.wirthandras.shifts.domain.employee.Employee;
import hu.wirthandras.shifts.domain.job.Job;
import hu.wirthandras.shifts.repository.EmployeeRepository;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepository repositoryEmployee;

	@Autowired
	private JobRepository repositoryJob;

	public Employee getEmployee(String idAsString) {
		long id = Long.parseLong(idAsString);
		return repositoryEmployee.findById(id).get();
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
