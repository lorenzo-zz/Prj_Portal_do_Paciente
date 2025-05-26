package com.example.oracleapi.DTO;

import com.example.oracleapi.Entity.Paciente;

public record PacienteAlergiaDTO(
    String descricao,
    String nomeAlergia,
    Paciente paciente) {
}
