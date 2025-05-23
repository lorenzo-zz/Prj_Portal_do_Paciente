package com.example.oracleapi.DTO;

import java.time.LocalDate;

public record PrescricaoDTO(
        String remedio,
        LocalDate data,
        String descricao
) {
}
