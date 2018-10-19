package hu.wirthandras.shifts.domain.shift;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class ShiftIntervalTest {

	@Test
	public void testDayIntervalDuration() {
		ShiftInterval shift = new ShiftInterval(6, 18, null);
		assertThat(12, equalTo(shift.getDuration()));
	}
	
	@Test
	public void testNightIntervalDuration() {
		ShiftInterval shift = new ShiftInterval(18, 6, null);
		assertThat(12, equalTo(shift.getDuration()));
	}
	
	@Test
	public void test24hShiftShouldBeGood() {
		ShiftInterval shift = new ShiftInterval(12, 12, null);
		assertThat(24, equalTo(shift.getDuration()));
	}

}
