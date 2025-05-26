package com.example.oracleapi.Entity;

import com.example.oracleapi.Model.NomeAlergia;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
@Table(name = "t09a_paciente_alergia")
public class PacienteAlergia {

        // Tabela ajustada // Completo! 

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int id;

    @NotBlank
    @Size(max = 200, message = "A descrição deve ter no máximo 200 caracteres")
    private String descricao;

    @NotNull
    @Enumerated(EnumType.STRING)
    private NomeAlergia nome;

    @ManyToOne
    private Paciente paciente;

    public int getId() {
        return id;
    }
}
