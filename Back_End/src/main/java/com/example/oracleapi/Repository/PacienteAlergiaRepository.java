package com.example.oracleapi.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.oracleapi.Entity.PacienteAlergia;

@Repository
public interface PacienteAlergiaRepository extends JpaRepository<PacienteAlergia, Integer> {
    
}
