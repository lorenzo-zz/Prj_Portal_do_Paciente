package com.example.oracleapi.DTO;

import com.example.oracleapi.Entity.AgendamentoConsulta;
import com.example.oracleapi.Entity.Medico;
import com.example.oracleapi.Entity.Paciente;

import java.time.LocalDate;
import java.time.LocalTime;

public record MinhaConsultaDTO(
        LocalDate data,
        LocalTime hora,
        String descricao,
        String resultado,
        char ativo,
        String consultaStatus,
        Paciente paciente,
        Medico medico,
        AgendamentoConsulta agendamentoConsulta,
        String frequencia,
        String pressaoArterial,
        String temperatura) {
}
