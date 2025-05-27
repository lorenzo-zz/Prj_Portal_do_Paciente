package com.example.oracleapi.DTO;

import java.time.LocalDate;
import java.time.LocalTime;

import com.example.oracleapi.Entity.AgendamentoConsulta;
import com.example.oracleapi.Model.EspecificacaoMedico;

public record RetornoAgendamentoDTO(
        String nomePaciente,
        LocalDate data,
        LocalTime hora,
        EspecificacaoMedico especificacaoMedico) {

    public RetornoAgendamentoDTO(String nomePaciente, LocalDate data, LocalTime hora,
            EspecificacaoMedico especificacaoMedico) {
        this.nomePaciente = nomePaciente;
        this.data = data;
        this.hora = hora;
        this.especificacaoMedico = especificacaoMedico;
    }

    public RetornoAgendamentoDTO(AgendamentoConsulta agendamentoConsulta) {
        this(agendamentoConsulta.getPaciente().getNome(),
                agendamentoConsulta.getData(),
                agendamentoConsulta.getHora(),
                agendamentoConsulta.getEspecificacaoMedico());
    }
}
