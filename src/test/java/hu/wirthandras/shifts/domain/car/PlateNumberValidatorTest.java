package hu.wirthandras.shifts.domain.car;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import javax.validation.ConstraintValidatorContext;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class PlateNumberValidatorTest {
	
	private PlateNumberValidator testClass;
	private ConstraintValidatorContext cvc;
	
	@Before
	public void setUp() {
		testClass = new PlateNumberValidator();
		cvc = Mockito.mock(ConstraintValidatorContext.class);
	}

	@Test
	public void testShouldAcceptPlateNumbers() {
		assertThat(true, is(testClass.isValid("AAA-001", cvc)));
		assertThat(true, is(testClass.isValid("ZZZ-999", cvc)));
	}
	
	@Test
	public void testIsValidShouldFailWhenPlateNumbersContainsOnlyLetters() {
		assertThat(false, is(testClass.isValid("AAAAAAA", cvc)));
	}
	
	@Test
	public void testIsValidShouldFailWhenPlateNumberShortenThanRequired() {
		assertThat(false, is(testClass.isValid("AA-001", cvc)));
	}
	
	@Test
	public void testIsValidShouldFailWhenPlateNumberLongerThanRequired() {
		assertThat(false, is(testClass.isValid("AAAA-001", cvc)));
	}
	
	@Test
	public void testIsValidShouldFailWhenPlateNumberLettersAndDigitsSwitched() {
		assertThat(false, is(testClass.isValid("001-AAA", cvc)));
	}
	
	@Test
	public void testIsValidShouldFailWhenPlateNumberContainsLowerCaseLetter() {
		assertThat(false, is(testClass.isValid("AaA-001", cvc)));
	}
	
	@Test
	public void testIsValidShouldFailWhenHyphenReplacedToSpace() {
		assertThat(false, is(testClass.isValid("AAA 001", cvc)));
	}
	
	
}
