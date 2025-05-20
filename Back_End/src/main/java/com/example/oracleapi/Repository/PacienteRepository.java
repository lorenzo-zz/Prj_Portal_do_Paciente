package com.example.oracleapi.Repository;

import com.example.oracleapi.Entity.Paciente;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PacienteRepository extends JpaRepository<Paciente, Integer> {

    @Modifying
    @Transactional
    @Query("UPDATE Paciente p SET p.documento = :caminho WHERE p.cpf = :cpf")
    void atualizarCaminhoDocumento(@Param("cpf") String cpf, @Param("caminho") String caminho);
}

