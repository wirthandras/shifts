package hu.wirthandras.shifts.services;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import hu.wirthandras.shifts.domain.shift.ShiftInterval;

public class IntervalServiceTest {

	private IntervalService service;

	@Rule
	public ExpectedException expected = ExpectedException.none();

	@Before
	public void setUp() {
		service = new IntervalService();
	}

	@Test
	public void testAddTwoDifferentInterval() throws ShiftIntervalAlreadyExistException {
		ShiftInterval si = new ShiftInterval(6, 18, null);
		ShiftInterval si2 = new ShiftInterval(18, 6, null);
		service.addInterval(si);
		service.addInterval(si2);
	}

	@Test
	public void testAddSameAsTwice() throws ShiftIntervalAlreadyExistException {
		expected.expect(ShiftIntervalAlreadyExistException.class);

		ShiftInterval si = new ShiftInterval(6, 18, null);
		service.addInterval(si);
		service.addInterval(si);
	}

}
