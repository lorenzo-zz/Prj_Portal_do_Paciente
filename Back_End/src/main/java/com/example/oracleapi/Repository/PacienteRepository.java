package com.example.oracleapi.Repository;

import com.example.oracleapi.Entity.Paciente;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Integer> {

    Optional<Paciente> findByCpf(String cpf);

    Optional<String> findCpfById(Integer id);

    Optional<String> findCpfById(Long id);

}
