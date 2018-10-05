package hu.wirthandras.shifts.domain.car;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * 
 * Validator to validate String field as Hungarian Car Plate Number.
 * 
 * @author Andras Wirth
 *
 */
public class PlateNumberValidator implements ConstraintValidator<PlateNumberConstraint, String> {
	
	private static final String pattern = "[A-Z]{3}-[0-9]{3}";

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		return value != null && value.matches(pattern) && (value.length() == 7);
	}

}
