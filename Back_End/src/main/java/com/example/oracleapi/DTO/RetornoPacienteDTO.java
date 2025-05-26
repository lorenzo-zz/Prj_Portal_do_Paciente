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
    public RetornoPacienteDTO(
            String nome,
            String telefone,
            String email,
            String dataNascimento,
            String cep,
            String logradouro,
            String numero,
            String complemento,
            String bairro,
            String cidade,
            String uf,
            String cpf
    ) {
        this(nome, telefone, email, LocalDate.parse(dataNascimento), cep, logradouro, numero, complemento, bairro, cidade, uf, cpf);
    }
}
