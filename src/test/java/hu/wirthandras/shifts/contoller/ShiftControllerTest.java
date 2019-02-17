package hu.wirthandras.shifts.contoller;

import static org.mockito.Mockito.mock;

import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.ui.Model;

import hu.wirthandras.shifts.exception.ShiftNotFoundException;
import hu.wirthandras.shifts.services.ShiftService;

public class ShiftControllerTest {

	@Rule
	public Timeout timeout = new Timeout(1, TimeUnit.SECONDS);

	private ShiftController controller;

	private ShiftService mockShiftService;
	private Model mockModel;

	@Before
	public void setUp() {
		controller = new ShiftController();

		mockShiftService = mock(ShiftService.class);
		mockModel = mock(Model.class);

		ReflectionTestUtils.setField(controller, "shiftService", mockShiftService);
	}

	@Test
	public void test() throws ShiftNotFoundException {
		Assert.assertNotNull(controller.shift("4", mockModel));
		Assert.assertEquals("shift/shift", controller.shift("4", mockModel));
	}

}
