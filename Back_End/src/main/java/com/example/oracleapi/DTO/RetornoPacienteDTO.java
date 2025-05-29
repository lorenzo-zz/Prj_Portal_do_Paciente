package com.example.oracleapi.DTO;

public record RetornoPacienteDTO(
        String cpf,
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
        String uf
) {

}
