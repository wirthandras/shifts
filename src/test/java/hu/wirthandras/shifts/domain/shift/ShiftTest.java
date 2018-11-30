package hu.wirthandras.shifts.domain.shift;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import hu.wirthandras.shifts.domain.Shift;

public class ShiftTest {

	private Shift sNight;
	private Shift sShort;

	@Before
	public void setUp() {
		sNight = new Shift(null, 18, 6, null);
		sShort = new Shift(null, 8, 15, null);
	}

	@Test
	public void testShiftDurationShouldBe12Hour() {
		Assert.assertEquals(12, sNight.duration());
	}

	@Test
	public void testShiftIsNightShouldBe7Hour() {
		Assert.assertEquals(7, sShort.duration());
	}

	@Test
	public void testToIntervalShouldBe8H15H() {
		Assert.assertEquals("8-15", sShort.toInterval());
	}

	@Test
	public void testToIntervalShouldBe18H6H() {
		Assert.assertEquals("18-6", sNight.toInterval());
	}
}
