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
    @Size(min = 8, max = 8, message = "O CEP deve ter 8 dígitos")
    private String cep;
    @NotBlank
    @Size(min = 10, max = 100, message = "O logradouro deve ter no máximo 100 caracteres")
    private String logradouro;
    @NotBlank
    @Size(min = 3, max = 30, message = "A cidade deve ter no máximo 30 caracteres")
    private String cidade;
    @NotBlank
    @Size(min = 2, max = 2, message = "A UF deve ter 2 caracteres")
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
}
