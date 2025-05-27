package com.example.oracleapi.Repository;

import com.example.oracleapi.Entity.Medico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface MedicoRepository  extends JpaRepository<Medico, Integer> {


    Optional<Medico> findByCrm(String medicoCrm);
}
