package com.example.oracleapi.Entity;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
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
public class Paciente implements UserDetails{
    
        // Tabela ajustada // Completo! 

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotBlank
    @Email
    private String email;
    @NotBlank
    @Size(min = 8, message = "A senha deve ter no mínimo 8 caracteres")
    private String senha;
    @NotBlank
    @CPF
    private String cpf;
    @NotBlank
    @Size(min = 1, max = 1)
    private char sexo;
    @NotBlank
    @Size(min = 11, max = 11, message = "O telefone deve ter 11 dígitos")
    private String telefone;
    @NotBlank
    @Size(min = 3, max = 100, message = "O nome deve ter no mínimo 3 e no máximo 100 caracteres")
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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(null));
    }

    @Override
    public String getPassword() {
        return this.getSenha();
    }

    @Override
    public String getUsername() {
        return this.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
