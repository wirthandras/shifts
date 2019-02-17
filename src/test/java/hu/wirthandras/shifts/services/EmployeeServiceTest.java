package hu.wirthandras.shifts.services;

import static org.mockito.Mockito.mock;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.springframework.test.util.ReflectionTestUtils;

import hu.wirthandras.shifts.exception.EmployeeNotFoundException;
import hu.wirthandras.shifts.repository.EmployeeRepository;

public class EmployeeServiceTest {
	
	@Rule
	public ExpectedException expected = ExpectedException.none(); 
	
	private EmployeeService service = new EmployeeService();
	
	private EmployeeRepository mockEmployeeRepository;
	
	@Before
	public void setUp() {
		service = new EmployeeService();
		
		mockEmployeeRepository = mock(EmployeeRepository.class);
		
		ReflectionTestUtils.setField(service, "repositoryEmployee", mockEmployeeRepository);
	}

	@Test
	public void testExpectEmployeeNotFoundException() throws EmployeeNotFoundException {
		
		expected.expect(EmployeeNotFoundException.class);
		
		service.getEmployee("2");
	}

}
