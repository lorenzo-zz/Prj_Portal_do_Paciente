package com.example.oracleapi.DTO;

public record AtualizarPacienteDTO(
        String cpf,
        String nomeCompleto,
        String telefone,
        String email,
        String cep,
        String logradouro,
        String numero,
        String complemento,
        String bairro,
        String cidade,
        String uf

) {

}
