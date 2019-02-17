package hu.wirthandras.shifts.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.rules.Timeout;
import org.springframework.test.util.ReflectionTestUtils;

import hu.wirthandras.shifts.domain.Shift;
import hu.wirthandras.shifts.exception.ShiftNotFoundException;
import hu.wirthandras.shifts.repository.ShiftRepository;

public class ShiftServiceTest {

	@Rule
	public Timeout timeout = new Timeout(1, TimeUnit.SECONDS);

	@Rule
	public ExpectedException expected = ExpectedException.none();

	private ShiftService service;

	private ShiftRepository mockShiftRepository;

	private Shift mockShift;

	@Before
	public void setUp() {
		service = new ShiftService();

		mockShiftRepository = mock(ShiftRepository.class);
		mockShift = mock(Shift.class);

		when(mockShiftRepository.findById(1l)).thenReturn(Optional.of(mockShift));

		ReflectionTestUtils.setField(service, "shiftRepository", mockShiftRepository);
	}

	@Test
	public void testExpectShiftNotFoundException() throws ShiftNotFoundException {

		expected.expect(ShiftNotFoundException.class);

		service.getShift("2");
	}

	@Test
	public void testGetShift() throws ShiftNotFoundException {
		assertNotNull(service.getShift("1"));
		assertEquals(mockShift, service.getShift("1"));
	}

}
