package com.example.oracleapi.DTO;

import com.example.oracleapi.Entity.Paciente;
import lombok.Data;
import org.springframework.validation.beanvalidation.SpringValidatorAdapter;

public record PacienteAlergiaDTO(
    String descricao,
    String nomeAlergia,
    Paciente paciente) {
}
