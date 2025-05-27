package com.example.oracleapi.DTO;

import com.example.oracleapi.Model.EspecificacaoMedico;
import java.time.LocalDate;
import java.time.LocalTime;

public record AgendamentoConsultaDTO(
                String descricao,
                String nomePaciente,
                String cpfPaciente,
                LocalDate data,
                String telefone,
                String email,
                EspecificacaoMedico especificacaoMedico,
                LocalTime hora) {
}
