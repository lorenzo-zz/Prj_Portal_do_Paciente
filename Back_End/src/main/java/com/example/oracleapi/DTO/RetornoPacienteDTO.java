package com.example.oracleapi.DTO;

import java.time.LocalDate;

public record RetornoPacienteDTO(
            String nome,
            String telefone,
            String email,
            LocalDate dataNascimento,
            String cep,
            String logradouro,
            String numero,
            String complemento,
            String bairro,
            String cidade,
            String uf,
            String cpf

) {
    
}
