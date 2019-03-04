package hu.wirthandras.shifts.domain.shift;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.openpojo.reflection.PojoClass;
import com.openpojo.reflection.impl.PojoClassFactory;
import com.openpojo.validation.Validator;
import com.openpojo.validation.ValidatorBuilder;
import com.openpojo.validation.rule.impl.GetterMustExistRule;
import com.openpojo.validation.rule.impl.SetterMustExistRule;
import com.openpojo.validation.test.impl.GetterTester;
import com.openpojo.validation.test.impl.SetterTester;

import hu.wirthandras.shifts.domain.car.type.CarType;

public class ShiftIntervalTest {

	private ShiftInterval partial;
	private ShiftInterval day;
	private ShiftInterval day2;
	private ShiftInterval day3;
	private ShiftInterval night;
	private ShiftInterval h24;
	
	private CarType carMock;
	private CarType carMock2;

	@Before
	public void setUp() {
		carMock = Mockito.mock(CarType.class);
		carMock2 = Mockito.mock(CarType.class);
		
		partial = new ShiftInterval(6, 14, carMock);
		day = new ShiftInterval(6, 18, carMock);
		day2 = new ShiftInterval(6, 18, carMock2);
		day3 = new ShiftInterval(7, 19, carMock);
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
	public void testGetterSetter() {
		PojoClass pojoclass = PojoClassFactory.getPojoClass(ShiftInterval.class);
		Validator validator = ValidatorBuilder.create()
				.with(new SetterMustExistRule())
				.with(new GetterMustExistRule())
				.with(new SetterTester())
				.with(new GetterTester())
				.build();
		validator.validate(pojoclass);
	}

}
