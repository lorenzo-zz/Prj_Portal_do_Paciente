package com.example.oracleapi.Service;

import com.example.oracleapi.DTO.*;
import com.example.oracleapi.Entity.Paciente;
import com.example.oracleapi.Exception.AlergiaException;
import com.example.oracleapi.Repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.sql.DataSource;
import java.sql.*;

@CrossOrigin(origins = "*")
@RequestMapping("/paciente")
@Service
public class PacienteService {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private PacienteRepository pacienteRepository;

    public void cadastrarAlergia(PacienteAlergiaDTO pacienteAlergiaDTO) throws SQLException {
        Paciente pacienteExistente = pacienteRepository.findByCpf(pacienteAlergiaDTO.paciente().getCpf())
                .orElseThrow(() -> new SQLException("Paciente não encontrado"));

        try (Connection conn = dataSource.getConnection()) {
            CallableStatement stmt = conn.prepareCall("{call proc_t09a_paciente_alergia(?, ?)}");

            stmt.setString(1, pacienteAlergiaDTO.nomeAlergia());
            stmt.setInt(2, pacienteExistente.getId());

            stmt.execute();

        } catch (SQLException e) {
            if (e.getErrorCode() == 20001) {
                throw new AlergiaException("Alergia já cadastrada para este paciente.");
            } else if (e.getErrorCode() == 20005) {
                throw new AlergiaException("Paciente não encontrado.");
            }
            throw new SQLException(e.getMessage(), e);
        }
    }

    public RetornoPacienteDTO dadosDoPaciente(String cpf) throws SQLException {
        try (Connection conn = dataSource.getConnection()) {
            CallableStatement stmt = conn
                    .prepareCall("{call proc_t09a_dados_paciente(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}");

            stmt.setString(1, cpf);

            stmt.registerOutParameter(2, Types.VARCHAR); 
            stmt.registerOutParameter(3, Types.VARCHAR); 
            stmt.registerOutParameter(4, Types.VARCHAR); 
            stmt.registerOutParameter(5, Types.DATE); 
            stmt.registerOutParameter(6, Types.VARCHAR); 
            stmt.registerOutParameter(7, Types.VARCHAR);
            stmt.registerOutParameter(8, Types.VARCHAR); 
            stmt.registerOutParameter(9, Types.VARCHAR); 
            stmt.registerOutParameter(10, Types.VARCHAR); 
            stmt.registerOutParameter(11, Types.VARCHAR); 
            stmt.registerOutParameter(12, Types.VARCHAR); 

            
            stmt.execute();

            return new RetornoPacienteDTO(
                    stmt.getString(2),
                    stmt.getString(3), 
                    stmt.getString(4), 
                    stmt.getDate(5) != null ? String.valueOf(stmt.getDate(5).toLocalDate()) : null, 
                    stmt.getString(6), 
                    stmt.getString(7), 
                    stmt.getString(8),
                    stmt.getString(9), 
                    stmt.getString(10), 
                    stmt.getString(11), 
                    stmt.getString(12), 
                    cpf 
            );
        } catch (SQLException e) {
            throw new SQLException("Erro generico" + e.getMessage());
        }
    }

    public void alergiaRemover(PacienteAlergiaDTO pacienteAlergiaDTO) throws SQLException {

        Paciente pacienteExistente = pacienteRepository.findByCpf(pacienteAlergiaDTO.paciente().getCpf())
                .orElseThrow(() -> new SQLException("Paciente não encontrado"));

        try (Connection conn = dataSource.getConnection()) {
            CallableStatement stmt = conn.prepareCall("{call proc_remover_alergia_paciente(?, ?)}");

            stmt.setString(1, pacienteAlergiaDTO.nomeAlergia());
            stmt.setInt(2, pacienteExistente.getId());

            stmt.execute();

        } catch (SQLException e) {
            throw new SQLException(e.getMessage(), e);
        }
    }
}
