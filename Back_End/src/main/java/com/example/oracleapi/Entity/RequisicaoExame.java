package com.example.oracleapi.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@ToString
@Entity
@Table(name = "t09a_requisicao_exame")
public class RequisicaoExame {
    
    // Tabela ajustada // Completo!
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotBlank
    @Size(max = 500, message = "A descrição deve ter no máximo 500 caracteres")
    private String descricao;
    @NotBlank
    @Size(max = 200, message = "O tipo de exame deve ter no máximo 200 caracteres")
    private String tipoExame;
    @NotBlank
    private String tipoConvenio;
    @NotBlank
    private String nomeDocumento;
    @NotNull
    @ManyToOne
    private Paciente paciente;
    public int getId() {
        return id;
    }
}
