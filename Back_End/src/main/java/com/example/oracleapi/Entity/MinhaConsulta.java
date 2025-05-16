package com.example.oracleapi.Entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity
@Table(name = "t09a_minha_consulta")
public class MinhaConsulta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    private LocalDate data;

    @NotNull
    private LocalTime hora;

    @NotBlank
    @Size(min = 10, max = 500)
    private String descricao;

    @NotBlank
    @Size(min = 10, max = 100)
    private String resultado;

    private char ativo;

    @Enumerated(EnumType.STRING)
    ConsultaStatus consultaStatus;

    @ManyToOne
    private Paciente paciente;

    @ManyToOne
    private Medico medico;

    @ManyToOne
    ResultadoExame resultadoExame;

    @ManyToOne
    AgendamentoConsulta agendamentoConsulta;


}
