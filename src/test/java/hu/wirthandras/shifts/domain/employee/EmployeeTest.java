package hu.wirthandras.shifts.domain.employee;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import hu.wirthandras.shifts.domain.job.Job;

public class EmployeeTest {

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
}
