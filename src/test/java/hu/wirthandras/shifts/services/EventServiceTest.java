package hu.wirthandras.shifts.services;

import static org.mockito.Mockito.mock;

import java.time.LocalDate;

import org.junit.Before;
import org.springframework.test.util.ReflectionTestUtils;

import hu.wirthandras.shifts.repository.EventEmployeeRepository;

//TODO add employee event tests
public class EventServiceTest {

	private static final int DAY_ID_INT = 20;
	private static final String DAY_ID = DAY_ID_INT + "";
	private LocalDate date = LocalDate.of(2000, 1, DAY_ID_INT);

	private EventService service;

	private EventEmployeeRepository mockEventEmployeeRepository;
	private EmployeeService mockEmployeeService;

	@Before
	public void setUp() {
		service = new EventService();

		mockEventEmployeeRepository = mock(EventEmployeeRepository.class);
		mockEmployeeService = mock(EmployeeService.class);

		ReflectionTestUtils.setField(service, "repositoryEventEmployee", mockEventEmployeeRepository);
		ReflectionTestUtils.setField(service, "serviceEmployee", mockEmployeeService);
	}

}
