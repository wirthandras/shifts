package hu.wirthandras.shifts.domain.shift;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Before;
import org.junit.Test;

public class ShiftIntervalTest {

	private ShiftInterval partial = new ShiftInterval(6, 14, null);
	private ShiftInterval day = new ShiftInterval(6, 18, null);
	private ShiftInterval night = new ShiftInterval(18, 6, null);
	private ShiftInterval h24 = new ShiftInterval(12, 12, null);

	@Before
	public void setUp() {
		partial = new ShiftInterval(6, 14, null);
		day = new ShiftInterval(6, 18, null);
		night = new ShiftInterval(18, 6, null);
	}

	@Test
	public void testDayIntervalDuration() {
		assertThat(12, equalTo(day.getDuration()));
	}

	@Test
	public void testNightIntervalDuration() {
		assertThat(12, equalTo(night.getDuration()));
	}

	@Test
	public void test24hShiftShouldBeGood() {
		assertThat(24, equalTo(h24.getDuration()));
	}

	@Test
	public void testAscendingOrdering() {
		List<ShiftInterval> list = Stream
				.of(day, night, partial, h24)
				.collect(Collectors.toList());

		Collections.sort(list);

		assertThat(partial, equalTo(list.get(0)));
		assertThat(day, equalTo(list.get(1)));
		assertThat(h24, equalTo(list.get(2)));
		assertThat(night, equalTo(list.get(3)));
	}

}
