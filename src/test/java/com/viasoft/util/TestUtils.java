package com.viasoft.util;

import java.lang.reflect.Field;

public class TestUtils {
    // define campo privado em objeto
    public static void setField(Object target, String fieldName, Object value) {
        try {
            // Obtém o campo privado declarado com o nome especificado
            Field field = target.getClass().getDeclaredField(fieldName);
            // Torna o campo acessível para modificação, mesmo que seja private
            field.setAccessible(true);
      // cria um novo valor
            field.set(target, value);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
