package com.example.oracleapi.Entity;

import com.example.oracleapi.Model.NomeAlergia;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity
@Table(name = "t09a_paciente_alergia")
public class PacienteAlergia {

        // Tabela ajustada // Completo! 

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int id;

    @NotNull
    @Enumerated(EnumType.STRING)
    private NomeAlergia nome;

    @ManyToOne
    private Paciente paciente;

    public int getId() {
        return id;
    }
}
