package com.example.oracleapi.Repository;

import com.example.oracleapi.Entity.MinhaConsulta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;
@Repository
public interface MinhaConsultaRepository extends JpaRepository<MinhaConsulta, Integer> {


    Optional<MinhaConsulta> findByPacienteMedicoDataHora(int paciente, String medico, LocalDate localDate, LocalTime localTime);

    Optional<MinhaConsulta> findByDataAndHora( LocalTime hora, LocalDate localDate);
}
