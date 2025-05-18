package com.viasoft.enums;

public enum Provedor {
    AWS, OCI;

    public static Provedor from(String value) {
        try {
//toUpperCase converter em letras maiusculas
            return Provedor.valueOf(value.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Provedor inválido: " + value);
        }
    }
}
