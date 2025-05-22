package com.example.oracleapi.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

import com.example.oracleapi.Model.EspecificacaoMedico;

@Data
@Entity
@Table(name = "t09a_agendamento_consulta")
public class AgendamentoConsulta {

    // Tabela ajustada // Completo!
    
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int id;

    @NotBlank
    @Size(max = 500, message = "A descrição deve ter no máximo 500 caracteres")
    private String descricao;

    @NotNull
    private LocalDate data;

    @NotNull
    private LocalTime hora;

    @ManyToOne
    private Paciente paciente;

    @Enumerated(EnumType.STRING)
    private EspecificacaoMedico especificacaoMedico;

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public LocalTime getHora() {
        return hora;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public EspecificacaoMedico getEspecificacaoMedico() {
        return especificacaoMedico;
    }

    public void setEspecificacaoMedico(EspecificacaoMedico especificacaoMedico) {
        this.especificacaoMedico = especificacaoMedico;
    }
}
