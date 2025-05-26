package com.example.oracleapi.Repository;

import com.example.oracleapi.Entity.AgendamentoConsulta;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

import com.example.oracleapi.Entity.MinhaConsulta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AgendamentoRepository extends JpaRepository<AgendamentoConsulta, Integer> {

    Optional<MinhaConsulta> findByDataAndHora(LocalDate data, LocalTime hora);
}
