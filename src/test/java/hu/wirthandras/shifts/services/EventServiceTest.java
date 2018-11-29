package hu.wirthandras.shifts.services;

import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.*;
import org.springframework.test.util.ReflectionTestUtils;

import hu.wirthandras.shifts.domain.car.Car;
import hu.wirthandras.shifts.domain.car.CarEvent;
import hu.wirthandras.shifts.repository.CarEmployeeRepository;
import hu.wirthandras.shifts.repository.EventEmployeeRepository;

public class EventServiceTest {

	private static final String CAR_ID = "1";
	private static final String DAY_ID = "20";

	private EventService service;

	private EventEmployeeRepository mockEventEmployeeRepository;
	private CarEmployeeRepository mockEventCarRepository;
	private EmployeeService mockEmployeeService;
	private CarService mockCarService;

	@Before
	public void setUp() {
		service = new EventService();

		mockEventEmployeeRepository = mock(EventEmployeeRepository.class);
		mockEventCarRepository = mock(CarEmployeeRepository.class);
		mockEmployeeService = mock(EmployeeService.class);
		mockCarService = mock(CarService.class);

		ReflectionTestUtils.setField(service, "repositoryEventEmployee", mockEventEmployeeRepository);
		ReflectionTestUtils.setField(service, "repositoryEventCar", mockEventCarRepository);
		ReflectionTestUtils.setField(service, "serviceEmployee", mockEmployeeService);
		ReflectionTestUtils.setField(service, "serviceCar", mockCarService);
	}

	@Test
	public void testGetCarEventsDaysMethodShouldReturnEmptySet() {
		Assert.assertNotNull(service.getCarEventsDays(DAY_ID));
		Assert.assertTrue(service.getCarEventsDays(DAY_ID).isEmpty());
	}

	@Test
	public void testGetCarEventsDaysShouldReturnOneEvent() {
		Car mockCar = mock(Car.class);
		when(mockCar.getId()).thenReturn(1l);
		when(mockCarService.getCar(CAR_ID)).thenReturn(mockCar);
		LocalDate date = service.resolveDateFromDayId(DAY_ID);
		List<CarEvent> list = new ArrayList<CarEvent>();
		list.add(new CarEvent(mockCar, date, null));
		when(mockEventCarRepository.findByCar(mockCar)).thenReturn(list);

		Assert.assertNotNull(service.getCarEventsDays(CAR_ID));
		Assert.assertEquals(1, service.getCarEventsDays(CAR_ID).size());

		verify(mockCarService, times(2)).getCar(anyString());
	}

}
