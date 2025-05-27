package com.example.oracleapi.Entity;

import java.time.LocalDate;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity
@Table(name = "t09a_paciente")
public class Paciente{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotBlank
    @Email
    private String email;
  
    @Size(min = 8, message = "A senha deve ter no mínimo 8 caracteres")
    private String senha;

    @NotBlank
    @Size(min = 11, max = 11)
    private String cpf;

    @NotNull(message = "O valor deve ser M ou F")
    private String sexo;

    @NotBlank
    @Size(min = 11, max = 11, message = "O telefone deve ter 11 dígitos")
    private String telefone;
    @NotBlank
    @Size(min = 3, max = 100, message = "O nome deve ter no mínimo 3 e no máximo 100 caracteres")
    private String nome;
    @NotNull
    private LocalDate dataNascimento;

    private String documento;

    private char ativo;

    private LocalDate dataCadastro;

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


    public int getId() {
        return id;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }


    public String getDocumento() {
        return documento;
    }

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }

    public String getCpf() {
        return cpf;
    }

    public String getSexo() {
        return sexo;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getNome() {
        return nome;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public char getAtivo() {
        return ativo;
    }

    public LocalDate getDataCadastro() {
        return dataCadastro;
    }


    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public void setDataCadastro(LocalDate dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public void setAtivo(char ativo) {
        this.ativo = ativo;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setId(int id) {
        this.id = id;
    }
}
