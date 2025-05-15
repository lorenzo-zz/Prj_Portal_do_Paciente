package com.example.oracleapi.Entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;

@Data
@EqualsAndHashCode(of = "id")
@Entity
@Table(name = "t09a_resultado_exame")
public class ResultadoExame {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank
    @Size(min = 10, max = 100)
    private String descricao;

    @NotNull
    private LocalDate date;

    @ManyToOne
    private Medico medico;

    @ManyToOne
    private Paciente paciente;

    @ManyToOne
    private Prescricao prescricao;

}
