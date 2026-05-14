package org.example.domain;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.Arrays;
import java.util.stream.Collectors;

public enum TipoCliente {
    PF, PJ;

    @JsonCreator
    public static TipoCliente fromValue(String value){
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException(
                    "Tipo ausente. Valores aceitos: " +
                            Arrays.stream(values()).map(Enum::name).collect(Collectors.joining(", "))
            );
        }
        try{
            return valueOf(value.toUpperCase());
        }catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(
                    "Tipo inválido: '" + value + "'. Valores aceitos: " +
                            Arrays.stream(values()).map(Enum::name).collect(Collectors.joining(", "))
            );
        }
    }
}