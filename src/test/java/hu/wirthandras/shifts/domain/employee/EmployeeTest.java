package hu.wirthandras.shifts.domain.employee;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class EmployeeTest {

	@Test
	public void testCompareTo() {
		Employee a = new Employee("A", Job.DOCTOR);
		Employee b = new Employee("B", Job.DRIVER);
		Employee a2 = new Employee("A", Job.DRIVER);
		
		assertEquals(-1, a.compareTo(b));
		assertEquals(1, b.compareTo(a));
		assertEquals(0, a.compareTo(a2));
		assertEquals(0, a2.compareTo(a));
	}
}
