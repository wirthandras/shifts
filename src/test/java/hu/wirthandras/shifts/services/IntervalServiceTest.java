package hu.wirthandras.shifts.services;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mockito;

import hu.wirthandras.shifts.domain.car.type.CarType;
import hu.wirthandras.shifts.domain.shift.ShiftInterval;
import hu.wirthandras.shifts.services.interval.IntervalService;
import hu.wirthandras.shifts.services.interval.ShiftIntervalAlreadyExistException;

public class IntervalServiceTest {

	private IntervalService service;
	private CarType carTypeMock;

	@Rule
	public ExpectedException expected = ExpectedException.none();

	@Before
	public void setUp() {
		service = new IntervalService();
		carTypeMock = Mockito.mock(CarType.class);
	}

	@Test
	public void testAddTwoDifferentInterval() throws ShiftIntervalAlreadyExistException {
		ShiftInterval si = new ShiftInterval(6, 18, carTypeMock);
		ShiftInterval si2 = new ShiftInterval(18, 6, carTypeMock);
		service.addInterval(si);
		service.addInterval(si2);
	}

	@Test
	public void testAddSameAsTwice() throws ShiftIntervalAlreadyExistException {
		ShiftInterval si = new ShiftInterval(6, 18, carTypeMock);
		service.addInterval(si);
		service.addInterval(si);
	}

}
