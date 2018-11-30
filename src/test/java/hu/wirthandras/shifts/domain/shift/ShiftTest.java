package hu.wirthandras.shifts.domain.shift;

import org.junit.Assert;
import org.junit.Test;

import hu.wirthandras.shifts.domain.Shift;

public class ShiftTest {

	@Test
	public void testShiftDurationShouldBe12Hour() {
		Shift shift = new Shift(null, 18, 6, null);
		Assert.assertEquals(12, shift.duration());
	}

	@Test
	public void testShiftIsNightShouldBe7Hour() {
		Shift shift = new Shift(null, 8, 15, null);
		Assert.assertEquals(7, shift.duration());
	}
}
