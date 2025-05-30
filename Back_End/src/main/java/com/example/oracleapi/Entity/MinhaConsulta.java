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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }

    public char getAtivo() {
        return ativo;
    }

    public void setAtivo(char ativo) {
        this.ativo = ativo;
    }

    public ConsultaStatus getConsultaStatus() {
        return consultaStatus;
    }

    public void setConsultaStatus(ConsultaStatus consultaStatus) {
        this.consultaStatus = consultaStatus;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    public AgendamentoConsulta getAgendamentoConsulta() {
        return agendamentoConsulta;
    }

    public void setAgendamentoConsulta(AgendamentoConsulta agendamentoConsulta) {
        this.agendamentoConsulta = agendamentoConsulta;
    }

    public String getFrequencia() {
        return frequencia;
    }

    public void setFrequencia(String frequencia) {
        this.frequencia = frequencia;
    }

    public String getPressao_arterial() {
        return pressao_arterial;
    }

    public void setPressao_arterial(String pressao_arterial) {
        this.pressao_arterial = pressao_arterial;
    }

    public String getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(String temperatura) {
        this.temperatura = temperatura;
    }
}
