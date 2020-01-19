package com.elias.videorental.validator;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.ConstraintValidator;

import org.springframework.stereotype.Service;

import net.bytebuddy.implementation.bind.MethodDelegationBinder.BindingResolver.Unique;
@Service
public class EliasValidator {

	public <T> Optional<List<ValidationError>> validate(T dto) {
		ArrayList<ValidationError> errors = new ArrayList<ValidationError>();
		Field[] fields = dto.getClass().getDeclaredFields();
		for (Field field : fields) {
			Annotation[] annotations = field.getAnnotations();
			for (Annotation annotation : annotations)  {
				if (annotation.annotationType().equals(javax.validation.Constraint.class)) {
				}
			}
		}
		return null;
	}
}
