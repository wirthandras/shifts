package hu.wirthandras.shifts.domain.car;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Constraint(validatedBy = PlateNumberValidator.class)
@Target( { ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface PlateNumberConstraint {
	String message() default "error.platenumber.format";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}