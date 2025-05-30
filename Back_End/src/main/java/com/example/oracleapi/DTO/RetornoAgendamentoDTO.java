package com.example.oracleapi.DTO;

import java.time.LocalDate;
import java.time.LocalTime;
import com.example.oracleapi.Entity.AgendamentoConsulta;
import com.example.oracleapi.Model.ConsultaStatus;
import com.example.oracleapi.Model.EspecificacaoMedico;

public record RetornoAgendamentoDTO(
        int id,
        String nomePaciente,
        LocalDate data,
        LocalTime hora,
        EspecificacaoMedico especificacaoMedico,
        ConsultaStatus status) {

    public RetornoAgendamentoDTO(int id, String nomePaciente, LocalDate data, LocalTime hora,
            EspecificacaoMedico especificacaoMedico, ConsultaStatus status) {
        this.id = id;
        this.nomePaciente = nomePaciente;
        this.data = data;
        this.hora = hora;
        this.especificacaoMedico = especificacaoMedico;
        this.status = status;
    }

    public RetornoAgendamentoDTO(AgendamentoConsulta agendamentoConsulta) {
        this(agendamentoConsulta.getId(),
                agendamentoConsulta.getPaciente().getNome(),
                agendamentoConsulta.getData(),
                agendamentoConsulta.getHora(),
                agendamentoConsulta.getEspecificacaoMedico(),
                agendamentoConsulta.getStatus());

    }
}
