package com.example.oracleapi.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity
@Table(name = "t09a_paciente")
public class Paciente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotBlank
    private String email;
    @NotBlank
    private String senha;
    @NotBlank
    private String cpf;
    @NotBlank
    private char sexo;
    @NotBlank
    private String telefone;
    @NotBlank
    private String nome;
    @NotNull
    private LocalDate dataNascimento;
    private char ativo;
    private LocalDate dataCadastro;
    @NotNull
    @ManyToOne
    private Endereco endereco;

    @Override
    public String toString() {
        return "Paciente{" +
                ", email='" + email + '\'' +
                ", cpf='" + cpf + '\'' +
                ", sexo=" + sexo +
                ", telefone='" + telefone + '\'' +
                ", nome='" + nome + '\'' +
                ", dataNascimento=" + dataNascimento +
                ", ativo=" + ativo +
                ", dataCadastro=" + dataCadastro +
                ", endereco=" + endereco +
                '}';
    }
}
