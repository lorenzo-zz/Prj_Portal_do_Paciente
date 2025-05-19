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
@Table(name = "t09a_documento")
public class Documento {
    
    // Tabela ajustada // Completo!
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotBlank
    @Size(max = 500, message = "O nome do documento deve ter no máximo 500 caracteres")
    private String nomeDocumento;

    @Override
    public String toString() {
        return "Documento{" +
                "id=" + id +
                ", nomeDocumento='" + nomeDocumento + '\'' +
                '}';
    }
}
