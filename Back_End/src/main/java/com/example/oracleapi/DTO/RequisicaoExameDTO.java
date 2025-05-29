package com.example.oracleapi.DTO;

import com.example.oracleapi.Entity.MinhaConsulta;

import java.time.LocalDate;
import java.time.LocalTime;

public record RequisicaoExameDTO(
  String pacienteCpf,
                String tipoExame,
                String tipoConvenio,
                String telefone,
                String email,
                String nomeDocumento,
                String observacoes,
                String medicoCrm) {
}