package com.example.oracleapi.DTO;

import com.example.oracleapi.Entity.RequisicaoExame;

public record ResultadoExameDTO(
        String descricao,
        RequisicaoExame requisicaoExame
) {
}
