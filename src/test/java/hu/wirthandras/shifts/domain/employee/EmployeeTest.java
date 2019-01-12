package hu.wirthandras.shifts.domain.employee;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.Before;
import org.junit.Test;

import hu.wirthandras.shifts.domain.job.Job;

public class EmployeeTest {

	private Employee a;

	private Validator validator;

	@Before
	public void setUp() {
		a = new Employee();
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}

	@Test
	public void testCompareTo() {
		Employee a = new Employee("A", new Job("DOCTOR"));
		Employee b = new Employee("B", new Job("DRIVER"));
		Employee a2 = new Employee("A", new Job("DRIVER"));

		assertEquals(-1, a.compareTo(b));
		assertEquals(1, b.compareTo(a));
		assertEquals(0, a.compareTo(a2));
		assertEquals(0, a2.compareTo(a));
	}

	@Test
	public void testWorkingTimePercentIsNegativeShouldFail() {
		a.setContractPercent(-5);
		Set<ConstraintViolation<Employee>> violations = validator.validate(a);
		assertFalse(violations.isEmpty());
	}

	@Test
	public void testWorkingTimePercentIsBiggerThanHundredShouldFail() {
		a.setContractPercent(101);
		Set<ConstraintViolation<Employee>> violations = validator.validate(a);
		assertFalse(violations.isEmpty());
	}

	@Test
	public void testWorkingTimePercentIsZeroShouldWork() {
		a.setContractPercent(0);
		Set<ConstraintViolation<Employee>> violations = validator.validate(a);
		assertTrue(violations.isEmpty());
	}

	@Test
	public void testWorkingTimePercentIsHundredShouldWork() {
		a.setContractPercent(100);
		Set<ConstraintViolation<Employee>> violations = validator.validate(a);
		assertTrue(violations.isEmpty());
	}

}
