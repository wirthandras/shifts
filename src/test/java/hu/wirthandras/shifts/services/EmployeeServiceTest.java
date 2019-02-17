package hu.wirthandras.shifts.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.rules.Timeout;
import org.springframework.test.util.ReflectionTestUtils;

import hu.wirthandras.shifts.domain.employee.Employee;
import hu.wirthandras.shifts.domain.job.Job;
import hu.wirthandras.shifts.exception.EmployeeNotFoundException;
import hu.wirthandras.shifts.repository.EmployeeRepository;

public class EmployeeServiceTest {

	private static final String JOB_MEDIC = "MEDIC";
	private static final String JOB_DRIVER = "DRIVER";

	@Rule
	public Timeout timeout = new Timeout(1, TimeUnit.SECONDS);

	@Rule
	public ExpectedException expected = ExpectedException.none();

	private EmployeeService service;

	private EmployeeRepository mockEmployeeRepository;

	private JobRepository mockJobRepository;

	private Employee mockEmployee;

	@Before
	public void setUp() throws EmployeeNotFoundException {
		service = new EmployeeService();

		mockEmployeeRepository = mock(EmployeeRepository.class);
		mockJobRepository = mock(JobRepository.class);
		mockEmployee = mock(Employee.class);

		Job job = mock(Job.class);
		when(job.getName()).thenReturn(JOB_MEDIC);
		when(mockEmployee.getJob()).thenReturn(job);

		Optional<Employee> optional = Optional.of(mockEmployee);

		when(mockEmployeeRepository.findById(1l)).thenReturn(optional);
		List<Employee> listEmployees = new ArrayList<Employee>();
		listEmployees.add(mockEmployee);
		when(mockEmployeeRepository.findAll()).thenReturn(listEmployees);

		List<Job> listJobs = Collections.emptyList();
		when(mockJobRepository.findAll()).thenReturn(listJobs);

		ReflectionTestUtils.setField(service, "repositoryEmployee", mockEmployeeRepository);
		ReflectionTestUtils.setField(service, "repositoryJob", mockJobRepository);
	}

	@Test
	public void testExpectEmployeeNotFoundException() throws EmployeeNotFoundException {

		expected.expect(EmployeeNotFoundException.class);

		service.getEmployee("2");
	}

	@Test
	public void testGetShift() throws EmployeeNotFoundException {
		assertNotNull(service.getEmployee("1"));
		assertEquals(mockEmployee, service.getEmployee("1"));
	}

	@Test
	public void testGetEmployeesShouldReturnOneElementList() {
		assertNotNull(service.getEmployees());
		assertEquals(1, service.getEmployees().size());
	}

	@Test
	public void testFilter() {
		assertNotNull(service.filter(JOB_MEDIC));
		assertEquals(1, service.filter(JOB_MEDIC).size());

		assertNotNull(service.filter(JOB_DRIVER));
		assertEquals(0, service.filter(JOB_DRIVER).size());

	}

	@Test
	public void testGetJobs() {
		assertNotNull(service.getJobs());
		assertEquals(0, service.getJobs().size());
	}

}
