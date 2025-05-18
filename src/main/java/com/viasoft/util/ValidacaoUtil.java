package com.viasoft.util;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;

import java.util.Set;

public class ValidacaoUtil {
    public static <T> void validar(T objeto, Validator validator) {
        Set<ConstraintViolation<T>> violations = validator.validate(objeto);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
    }
}
