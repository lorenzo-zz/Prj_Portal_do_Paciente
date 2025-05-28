package com.example.oracleapi.DTO;

public record RetornoPacienteDTO(
        String cpf,
        String nome,
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

        public RetornoPacienteDTO(String cpf, String nome, String telefone, String email, String cep,
                                                         String logradouro, String numero, String complemento, String bairro,
                                                         String cidade, String uf) {
                this.cpf = cpf;
                this.nome = nome;
                this.telefone = telefone;
                this.email = email;
                this.cep = cep;
                this.logradouro = logradouro;
                this.numero = numero;
                this.complemento = complemento;
                this.bairro = bairro;
                this.cidade = cidade;
                this.uf = uf;
        }    
}
