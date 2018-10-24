package hu.wirthandras.shifts.services;

import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mockito;

import hu.wirthandras.shifts.domain.car.Car;
import hu.wirthandras.shifts.domain.shift.ShiftInterval;
import hu.wirthandras.shifts.services.interval.CarLockedException;
import hu.wirthandras.shifts.services.interval.IntervalService;
import hu.wirthandras.shifts.services.interval.ShiftIntervalAlreadyExistException;

public class IntervalServiceTest {

	private IntervalService service;
	private Car carMock;

	@Rule
	public ExpectedException expected = ExpectedException.none();

	@Before
	public void setUp() {
		service = new IntervalService();
		carMock = Mockito.mock(Car.class);
		when(carMock.getPlateNumber()).thenReturn("AAA-111");
	}

	@Test
	public void testAddTwoDifferentInterval() throws ShiftIntervalAlreadyExistException, CarLockedException {
		ShiftInterval si = new ShiftInterval(6, 18, carMock);
		ShiftInterval si2 = new ShiftInterval(18, 6, carMock);
		service.addInterval(si);
		service.addInterval(si2);
	}

	@Test
	public void testAddSameAsTwice() throws ShiftIntervalAlreadyExistException, CarLockedException {
		expected.expect(CarLockedException.class);

		ShiftInterval si = new ShiftInterval(6, 18, carMock);
		service.addInterval(si);
		service.addInterval(si);
	}

}
