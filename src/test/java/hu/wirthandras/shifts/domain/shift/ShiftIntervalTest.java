package hu.wirthandras.shifts.domain.shift;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import hu.wirthandras.shifts.domain.car.Car;

public class ShiftIntervalTest {

	private ShiftInterval partial;
	private ShiftInterval day;
	private ShiftInterval day2;
	private ShiftInterval night;
	private ShiftInterval h24;
	
	private Car carMock;
	private Car carMock2;

	@Before
	public void setUp() {
		carMock = Mockito.mock(Car.class);
		when(carMock.getPlateNumber()).thenReturn("AAA-111");
		carMock2 = Mockito.mock(Car.class);
		when(carMock2.getPlateNumber()).thenReturn("BBB-222");
		
		partial = new ShiftInterval(6, 14, carMock);
		day = new ShiftInterval(6, 18, carMock);
		day2 = new ShiftInterval(6, 18, carMock2);
		night = new ShiftInterval(18, 6, carMock);
		h24 = new ShiftInterval(12, 12, carMock);
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
	
	@Test
	public void testIsCarLockedNoConflict() {
		assertThat(false, is(day.isCarLocked(night)));
		assertThat(false, is(night.isCarLocked(day)));
		assertThat(false, is(night.isCarLocked(partial)));
		assertThat(false, is(partial.isCarLocked(night)));
	}
	
	@Test
	public void testIsCarLockedHasConflict() {
		assertThat(true, is(day.isCarLocked(partial)));
		assertThat(true, is(partial.isCarLocked(day)));
	}
	
	@Test
	public void testIsCarLockedHasNoConflictWithDifferentCarAndSameTime() {
		assertThat(false, is(day.isCarLocked(day2)));
	}

}
