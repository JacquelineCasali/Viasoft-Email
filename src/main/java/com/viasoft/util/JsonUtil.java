package com.viasoft.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

// converter objeto em JSON
public class JsonUtil {

//  ObjectMapper usado para reutilização de objetos
    private static final ObjectMapper mapper = new ObjectMapper();

    // converte um objeto java em string JSON
    public static String serialize(Object obj) {
        try {
            return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Erro ao converter objeto para JSON", e);
        }
    }
}
