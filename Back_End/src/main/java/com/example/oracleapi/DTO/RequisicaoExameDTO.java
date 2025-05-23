package com.example.oracleapi.DTO;

import com.example.oracleapi.Entity.MinhaConsulta;

import java.time.LocalDate;
import java.time.LocalTime;

public record RequisicaoExameDTO(
    LocalDate dataRequisicao,
    LocalTime horaRequisicao,
    String descricao,
    String tipoExame,
    MinhaConsulta minhaConsulta,
    String tipoConvenio,
    String nomeDocumento
) {
}
