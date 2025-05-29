package com.example.oracleapi.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

import com.example.oracleapi.Model.ConsultaStatus;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity
@Table(name = "t09a_minha_consulta")
public class MinhaConsulta {

    // Tabela ajustada // Completo!

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    private LocalDate data;

    @NotNull
    private LocalTime hora;

    @NotBlank
    @Size(max = 500, message = "A descrição deve ter no máximo 500 caracteres")
    private String descricao;

    @NotBlank
    @Size(max = 250, message = "O resultado deve ter no máximo 250 caracteres")
    private String resultado;

    private char ativo;

    @Enumerated(EnumType.STRING)
    private ConsultaStatus consultaStatus;

    @ManyToOne
    private Paciente paciente;

    @ManyToOne
    @JoinColumn(name = "medico_id")
    private Medico medico;

    @OneToOne
    private AgendamentoConsulta agendamentoConsulta;

    private String frequencia;

    private String pressao_arterial;

    private String temperatura;

    public int getId() {
        return id;
    }
}
