package com.example.oracleapi.DTO;

import com.example.oracleapi.Entity.Paciente;
import com.example.oracleapi.Model.EspecificacaoMedico;

import java.time.LocalDate;
import java.time.LocalTime;

public record AgendamentoConsultaDTO(
        String descricao,
        LocalDate data,
        LocalTime hora,
        Paciente paciente,
        EspecificacaoMedico especificacaoMedico
) {
}
