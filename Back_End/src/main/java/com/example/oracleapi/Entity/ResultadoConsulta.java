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
@Table(name = "t09a_resultado_consulta")
public class ResultadoConsulta {

        // Tabela ajustada // Completo! 

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank
    @Size(max = 500, message = "A descrição deve ter no máximo 500 caracteres")
    private String descricao;

    @NotNull
    private LocalDate dataResultado;

    @ManyToOne
    private Medico medico;

    @ManyToOne
    private Paciente paciente;

    @OneToOne
    private Prescricao prescricao;

    @OneToOne
    private MinhaConsulta minhaConsulta;

    public int getId() {
        return id;
    }
}
