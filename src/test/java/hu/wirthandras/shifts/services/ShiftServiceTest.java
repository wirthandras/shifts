package hu.wirthandras.shifts.services;

import org.junit.Before;

import hu.wirthandras.shifts.domain.car.type.CarType;

public class ShiftServiceTest {

	private ShiftService classToTest;
	
	private CarType kim;
	private CarType eset;
	private CarType one;

	@Before
	public void setUp() {
		classToTest = new ShiftService();

		kim = new CarType("KIM");
		eset = new CarType("ESETKOCSI");
		one = new CarType("ONE");
	}

}
