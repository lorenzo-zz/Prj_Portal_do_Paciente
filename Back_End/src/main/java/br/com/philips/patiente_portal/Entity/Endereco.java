package br.com.philips.patiente_portal.Entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.builder.HashCodeExclude;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity
public class Endereco {

    @Id
    @NotBlank
    private Long id;

    @NotBlank
    @Size(min = 11, max = 30)
    @Email
    private String email;

    private  String senha;
    private  String cpf;
    private String telefone;
    private String nome;
    private LocalDate date;

}
