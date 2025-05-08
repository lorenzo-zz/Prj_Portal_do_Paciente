package br.com.philips.patiente_portal.Entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.builder.HashCodeExclude;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity
public class Endereco {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(min = 8, max = 8)
    private String cep;

    @NotBlank
    @Size(min = 50, max = 100)
    private String logradouro;

    @NotBlank
    @Size(min = 20, max = 30)
    private String cidade;

    @NotBlank
    @Size(min = 1, max = 2)
    private Character uf;

    @NotBlank
    @Size(min = 70, max = 100)
    private String bairro;

    @Size(min = 300, max = 500)
    private String complemento;

    @Size(min = 80, max = 100)
    private String numero;

    @ManyToOne
    @JoinColumn(name = "id_paciente")
    private Paciente paciente;
}
