package com.example.oracleapi.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@EqualsAndHashCode(of = "id")
@Entity
@Table(name = "t09a_prescricao")
public class Prescricao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String remedio;

    @NotNull
    private LocalDate data;

    @NotBlank
    @Size(min = 10, max = 500)
    private String descricao;

    @Override
    public String toString() {
        return "Prescricao{" +
                "id=" + id +
                ", remedio='" + remedio + '\'' +
                ", data=" + data +
                ", descricao='" + descricao + '\'' +
                '}';
    }
}
