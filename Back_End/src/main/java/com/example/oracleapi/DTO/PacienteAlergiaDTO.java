package com.example.oracleapi.DTO;

import com.example.oracleapi.Entity.Paciente;

public record PacienteAlergiaDTO(
    String nomeAlergia,
    Paciente paciente) {
}
