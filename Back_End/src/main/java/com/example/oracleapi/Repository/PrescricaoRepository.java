package com.example.oracleapi.Repository;

import com.example.oracleapi.Entity.Prescricao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PrescricaoRepository extends JpaRepository<Prescricao, Integer> {


    Optional<Prescricao> findByDescricao(String s);
}
