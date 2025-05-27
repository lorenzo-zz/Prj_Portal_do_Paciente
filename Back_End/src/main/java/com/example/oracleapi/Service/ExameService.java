package com.example.oracleapi.Service;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import javax.sql.DataSource;
import java.sql.Date;
import java.sql.RowId;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.oracleapi.DTO.RequisicaoExameDTO;
import com.example.oracleapi.DTO.ResultadoExameDTO;
import com.example.oracleapi.Repository.MedicoRepository;
import com.example.oracleapi.Repository.MinhaConsultaRepository;
import com.example.oracleapi.Repository.PacienteRepository;

@Service
public class ExameService {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private MinhaConsultaRepository minhaConsultaRepository;

    public void cadastrarResultadoExame(ResultadoExameDTO resultadoExameDTO) {
        try (Connection conn = dataSource.getConnection();
                CallableStatement stmt = conn.prepareCall("{call proc_t09a_resultado_exame(?, ?)}");) {

            stmt.setString(1, String.valueOf(Date.valueOf(resultadoExameDTO.descricao())));
            stmt.setRowId(2, (RowId) resultadoExameDTO.requisicaoExame());

            stmt.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void cadastrarRequisicaoExame(RequisicaoExameDTO requisicaoExameDTO) throws SQLException {
        try (Connection conn = dataSource.getConnection()) {
            CallableStatement stmt = conn.prepareCall("call proc_t09a_requisicao_exame(?,?,?,?,?,?,?)");

            int paciente = pacienteRepository.findByCpf(requisicaoExameDTO.pacienteCpf())
                    .orElseThrow(() -> new SQLException("Paciente não encontrado"))
                    .getId();

            int medico = medicoRepository.findByCrm(requisicaoExameDTO.medicoCrm())
                    .orElseThrow(() -> new SQLException("Médico não encontrado"))
                    .getId();

            int minhaConsulta = minhaConsultaRepository.findByPacienteAndMedicoAndDataAndHora(
                    paciente,
                    medico,
                    requisicaoExameDTO.dataRequisicao(),
                    requisicaoExameDTO.horaRequisicao()).orElseThrow(() -> new SQLException("Consulta não encontrada"))
                    .getId();

            stmt.setString(1, String.valueOf(requisicaoExameDTO.dataRequisicao()));
            stmt.setString(2, String.valueOf(requisicaoExameDTO.horaRequisicao()));
            stmt.setString(3, requisicaoExameDTO.descricao());
            stmt.setString(4, requisicaoExameDTO.tipoExame());
            stmt.setInt(5, minhaConsulta);
            stmt.setString(6, requisicaoExameDTO.tipoExame());
            stmt.setString(7, requisicaoExameDTO.nomeDocumento());

            stmt.execute();

        } catch (SQLException e) {
            throw new SQLException("Erro ao processar a requisição do exame");
        }
    }
}
