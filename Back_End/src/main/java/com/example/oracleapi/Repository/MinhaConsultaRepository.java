package com.example.oracleapi.Repository;

import com.example.oracleapi.Entity.MinhaConsulta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

public interface MinhaConsultaRepository extends JpaRepository<MinhaConsulta, Integer> {


    Optional<Object> findByPacienteMedicoDataHora(int paciente, int medico, LocalDate localDate, LocalTime localTime);
}
