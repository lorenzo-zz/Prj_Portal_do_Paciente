package com.example.oracleapi.Repository;

import com.example.oracleapi.Entity.MinhaConsulta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

@Repository
public interface MinhaConsultaRepository extends JpaRepository<MinhaConsulta, Integer> {

    @Query("SELECT m FROM MinhaConsulta m WHERE m.paciente.id = :pacienteId AND m.medico.id = :medicoId")
    Optional<MinhaConsulta> findByPacienteAndMedico(
            @Param("pacienteId") int pacienteId,
            @Param("medicoId") int medicoId);

    List<MinhaConsulta> findByAgendamentoConsultaId(Integer idAgendamento);

    

}
