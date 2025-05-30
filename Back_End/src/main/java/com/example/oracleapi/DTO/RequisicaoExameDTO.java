package com.example.oracleapi.DTO;

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