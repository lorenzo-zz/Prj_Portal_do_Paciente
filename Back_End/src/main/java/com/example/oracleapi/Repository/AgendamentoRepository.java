package com.example.oracleapi.Repository;

import com.example.oracleapi.Entity.AgendamentoConsulta;
import com.example.oracleapi.Entity.Paciente;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

import com.example.oracleapi.Entity.MinhaConsulta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AgendamentoRepository extends JpaRepository<AgendamentoConsulta, Integer> {

    Optional<MinhaConsulta> findByDataAndHora(LocalDate data, LocalTime hora);


    Optional<Object> findByPacienteIdAndDataAndHora(int paciente, LocalDate data, LocalTime hora);

<<<<<<< HEAD
    Optional<Paciente> findByPacienteCpf(String cpf);
=======
>>>>>>> 569ff7bac1ba1cf60419652be69a14302c298b65
}
