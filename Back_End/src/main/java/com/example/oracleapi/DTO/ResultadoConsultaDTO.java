package com.example.oracleapi.DTO;

import com.example.oracleapi.Entity.Medico;
import com.example.oracleapi.Entity.MinhaConsulta;
import com.example.oracleapi.Entity.Paciente;
import com.example.oracleapi.Entity.Prescricao;

import java.time.LocalDate;
import java.time.LocalTime;

public record ResultadoConsultaDTO(
        String pacienteCpf,
        String medicoCrm,
        LocalTime hora,
        String prescricaoDescricao,

        String descricao,
        LocalDate dataResultado,
        Medico medico,
        Paciente paciente,
        Prescricao prescricao,
        MinhaConsulta minhaConsulta
) {
}
