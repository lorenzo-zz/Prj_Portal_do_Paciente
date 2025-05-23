package com.example.oracleapi.DTO;

import com.example.oracleapi.Model.EspecificacaoMedico;

public record MedicoDTO(
    String nome,
    char ativo,
    EspecificacaoMedico especificacaoMedico,
    String crm
) {
}
