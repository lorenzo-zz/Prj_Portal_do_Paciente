package com.example.oracleapi.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
@Table(name = "t09a_paciente_alergia")
public class PacienteAlergia {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int id;

    @NotBlank
    @Size(min = 10, max = 500)
    private String descricao;

    @NotNull
    @Enumerated(EnumType.STRING)
    private NomeAlergia nome;

    @ManyToOne
    private Paciente paciente;

}
