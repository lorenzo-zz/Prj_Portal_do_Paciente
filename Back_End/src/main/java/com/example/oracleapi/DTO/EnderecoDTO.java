package com.example.oracleapi.DTO;

public record EnderecoDTO(
        String nomePaciente,
        String cep,
        String logradouro,
        String cidade,
        String uf,
        String bairro,
        String complemento,
        String numero
) {
    
}
