package com.nagarro.NagarroTravelPortal.entity.constraint;

import java.lang.annotation.Documented;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.annotation.ElementType;
import javax.validation.Constraint;
import javax.validation.ReportAsSingleViolation;
import javax.validation.constraints.Pattern;


@Documented
@Constraint(validatedBy={})
@Target({ElementType.METHOD,ElementType.FIELD,ElementType.ANNOTATION_TYPE,ElementType.CONSTRUCTOR,ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
 @Pattern(regexp="^[_A-Za-z0-9-\\\\+]+(\\\\.[_A-Za-z0-9-]+)*@nagarro.com")

public @interface Email
{
}