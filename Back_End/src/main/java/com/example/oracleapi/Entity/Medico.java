package com.example.oracleapi.Entity;

import com.example.oracleapi.Model.EspecificacaoMedico;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "t09a_medico")
public class Medico {

        // Tabela ajustada // Completo! 

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank
    @Size(max = 100, message = "O nome deve ter no máximo 100 caracteres")
    private String nome;

    private char ativo;

    @NotNull
    @Enumerated(EnumType.STRING)
    private EspecificacaoMedico especificacaoMedico;

    @NotBlank
    @Size(max = 15, message = "O CRM deve ter no máximo 15 caracteres")
    private String crm;

    public int getId() {
        return id;
    }
}
