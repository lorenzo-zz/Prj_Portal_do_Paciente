package com.example.oracleapi.DTO;

public record EnderecoDTO(
        String cpfPaciente,
        String cep,
        String logradouro,
        String cidade,
        String uf,
        String bairro,
        String complemento,
        String numero
) {
    
}
