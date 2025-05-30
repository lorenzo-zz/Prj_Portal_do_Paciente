package com.example.oracleapi.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.oracleapi.Entity.RequisicaoExame;

@Repository
public interface ExameRepository extends JpaRepository<RequisicaoExame, Integer> {

}
