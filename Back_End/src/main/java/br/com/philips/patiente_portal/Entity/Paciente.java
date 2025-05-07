package br.com.philips.patiente_portal.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;
@Data
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Paciente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(min = 11, max = 30)
    @Email
    private String email;

    @NotBlank
    @Size(min = 10, max = 30)
    private String senha;

    @NotBlank
    @Size(min = 11, max = 11)
    private String cpf;

    @NotBlank
    @Size(min = 13, max = 15)
    private String telefone;

    @NotBlank
    @Size(min = 10, max = 100)
    private String nome;

    @NotNull
    private LocalDate date;

    @NotNull
    @OneToMany(mappedBy = "id_endereco")
    private Endereco endereco;
}
