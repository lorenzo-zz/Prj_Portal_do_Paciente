package com.example.oracleapi.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity
@Table(name = "t09a_endereco")
public class Endereco {

    // Tabela ajustada // Completo! 

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotBlank
    @Size( max = 9, message = "O CEP deve ter 8 dígitos")
    private String cep;
    @NotBlank
    @Size(min = 10, max = 100, message = "O logradouro deve ter no máximo 100 caracteres")
    private String logradouro;
    @NotBlank
    @Size(min = 3, max = 30, message = "A cidade deve ter no máximo 30 caracteres")
    private String cidade;
    @NotBlank
    private String uf;
    @NotBlank
    @Size(max = 50, message = "O bairro deve ter no máximo 100 caracteres")
    private String bairro;
    private String complemento;
    private String numero;

    @Override
    public String toString() {
        return "Endereco{" +
                "id=" + id +
                ", cep='" + cep + '\'' +
                ", logradouro='" + logradouro + '\'' +
                ", uf=" + uf +
                ", bairro='" + bairro + '\'' +
                ", complemento='" + complemento + '\'' +
                ", numero='" + numero + '\'' +
                '}';
    }


    public String getCep() {
        return cep;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public String getCidade() {
        return cidade;
    }

    public String getUf() {
        return uf;
    }

    public String getBairro() {
        return bairro;
    }

    public String getComplemento() {
        return complemento;
    }

    public String getNumero() {
        return numero;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }
}
