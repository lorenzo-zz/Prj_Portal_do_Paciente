package com.example.oracleapi.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "t09a_paciente_documento")
public class PacienteDocumento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotNull
    @ManyToOne
    private Paciente paciente;
    @NotNull
    @ManyToOne
    private Documento documento;

    @Override
    public String toString() {
        return "PacienteDocumento{" +
                ", paciente=" + paciente +
                ", documento=" + documento +
                '}';
    }
}
