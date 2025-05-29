package com.example.oracleapi.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.time.LocalDate;
import java.time.LocalTime;

import com.example.oracleapi.Model.ConsultaStatus;
import com.example.oracleapi.Model.EspecificacaoMedico;

@Data
@Entity
@Table(name = "t09a_agendamento_consulta")
public class AgendamentoConsulta {

    // Tabela ajustada // Completo!

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int id;

    @NotNull
    private LocalDate data;

    @NotNull
    private LocalTime hora;

    @ManyToOne
    private Paciente paciente;

    @Enumerated(EnumType.STRING)
    private EspecificacaoMedico especificacaoMedico;

   public int getId() {
        return id;
    }

    @NotBlank
    private String telefone;

    @NotBlank
    private String email;

    @Enumerated(EnumType.STRING)
    private ConsultaStatus status;

    public void setData(LocalDate data) {
        this.data = data;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public void setEspecificacaoMedico(EspecificacaoMedico especificacaoMedico) {
        this.especificacaoMedico = especificacaoMedico;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setStatus(ConsultaStatus status) {
        this.status = status;
    }

    public LocalDate getData() {
        return data;
    }

    public LocalTime getHora() {
        return hora;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public EspecificacaoMedico getEspecificacaoMedico() {
        return especificacaoMedico;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getEmail() {
        return email;
    }

    public ConsultaStatus getStatus() {
        return status;
    }
}
