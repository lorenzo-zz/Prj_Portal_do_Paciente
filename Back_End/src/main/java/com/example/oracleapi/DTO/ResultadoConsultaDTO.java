package com.example.oracleapi.DTO;

import com.example.oracleapi.Entity.Medico;
import com.example.oracleapi.Entity.MinhaConsulta;
import com.example.oracleapi.Entity.Paciente;
import com.example.oracleapi.Entity.Prescricao;

import java.time.LocalDate;

public record ResultadoConsultaDTO(
        String descricao,
        LocalDate dataResultado,
        Medico medico,
        Paciente paciente,
        Prescricao prescricao,
        MinhaConsulta minhaConsulta
) {
}
