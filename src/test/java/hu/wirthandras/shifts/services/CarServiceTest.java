package hu.wirthandras.shifts.services;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.times;

import java.util.Optional;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mockito;
import org.springframework.test.util.ReflectionTestUtils;

import hu.wirthandras.shifts.domain.car.Car;
import hu.wirthandras.shifts.domain.car.type.CarType;
import hu.wirthandras.shifts.repository.CarRepository;

public class CarServiceTest {

	private static final String PLATE_NUMBER = "AAA-001";

	private CarType one = new CarType("ONE");

	@Rule
	public ExpectedException thrown = ExpectedException.none();

	private CarService service;

	private CarRepository repo;

	@Before
	public void setUp() {
		service = new CarService();
		repo = Mockito.mock(CarRepository.class);
		ReflectionTestUtils.setField(service, "repository", repo);
	}

	@Test
	public void testGetShouldBeWorkOnWhenGetElement() {
		Optional<Car> value = Optional.of(new Car(PLATE_NUMBER, one));
		Mockito.when(repo.findById(0l)).thenReturn(value);

		assertThat(PLATE_NUMBER, is(service.getCar("0").getPlateNumber()));
		assertThat(one, is(service.getCar("0").getCarType()));

		Mockito.verify(repo, times(2)).findById(0l);
	}

	@Test
	public void testGetShouldBeNumberFormatExceptionWhenParameterIsNotLong() {
		thrown.expect(NumberFormatException.class);

		assertThat(PLATE_NUMBER, is(service.getCar("a").getPlateNumber()));
	}

}
