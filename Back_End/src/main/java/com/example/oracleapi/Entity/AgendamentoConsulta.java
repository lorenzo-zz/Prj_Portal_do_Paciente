package com.example.oracleapi.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Entity
@Table(name = "t09a_agendamento_consulta")
public class AgendamentoConsulta {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int id;

    @NotBlank
    @Size(min = 10, max = 500)
    private String descricao;

    @NotNull
    private LocalDate data;

    @NotNull
    private LocalTime hora;

    @ManyToOne
    private Paciente paciente;
}
