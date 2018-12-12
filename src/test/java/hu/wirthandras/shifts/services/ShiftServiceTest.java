package hu.wirthandras.shifts.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.test.util.ReflectionTestUtils;

import hu.wirthandras.shifts.domain.car.Car;
import hu.wirthandras.shifts.domain.car.type.CarType;

public class ShiftServiceTest {

	private ShiftService classToTest;

	private CarService carServiceMock;

	private Car car1;
	private Car car2;
	private Car car3;
	private Car car4;
	
	private CarType kim;
	private CarType eset;
	private CarType one;

	private List<Car> cars;

	@Before
	public void setUp() {
		classToTest = new ShiftService();
		carServiceMock = mock(CarService.class);
		
		kim = new CarType("KIM");
		eset = new CarType("ESETKOCSI");
		one = new CarType("ONE");

		createTestCars();

		when(carServiceMock.getCars()).thenReturn(cars);

		ReflectionTestUtils.setField(classToTest, "carService", carServiceMock);
	}

	private void createTestCars() {
		car1 = new Car("HHH-111", kim);
		car2 = new Car("BBB-222", eset);
		car3 = new Car("ZZZ-333", one);
		car4 = new Car("AAA-444", kim);

		cars = new ArrayList<>();
		cars.add(car1);
		cars.add(car2);
		cars.add(car3);
		cars.add(car4);
	}

	@Test
	public void testGetCarsOrdering() {
		List<Car> cars = classToTest.getCars();

		assertNotNull(cars);
		assertEquals(4, cars.size());
		assertEquals(car2, cars.get(0));
		assertEquals(car4, cars.get(1));
		assertEquals(car1, cars.get(2));
		assertEquals(car3, cars.get(3));
	}

}
