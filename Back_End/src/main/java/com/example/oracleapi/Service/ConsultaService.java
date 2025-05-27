package com.example.oracleapi.Service;

import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.Time;
import java.util.List;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.Date;
import java.sql.RowId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.oracleapi.DTO.AgendamentoConsultaDTO;
import com.example.oracleapi.DTO.CpfDTO;
import com.example.oracleapi.DTO.MinhaConsultaDTO;
import com.example.oracleapi.DTO.PrescricaoDTO;
import com.example.oracleapi.DTO.ResultadoConsultaDTO;
import com.example.oracleapi.DTO.RetornoAgendamentoDTO;
import com.example.oracleapi.Entity.AgendamentoConsulta;
import com.example.oracleapi.Repository.AgendamentoRepository;
import com.example.oracleapi.Repository.MedicoRepository;
import com.example.oracleapi.Repository.PacienteRepository;

@Service
public class ConsultaService {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private AgendamentoRepository agendamentoRepository;

    public void agendarConsulta(AgendamentoConsultaDTO agendamentoConsulta) throws SQLException {
        try (Connection conn = dataSource.getConnection()) {
            CallableStatement stmt = conn.prepareCall("{call proc_t09a_agendamento_consulta(?, ?, ?, ?, ?, ?, ?)}");

            stmt.setString(1, agendamentoConsulta.nomePaciente());
            stmt.setString(2, agendamentoConsulta.cpfPaciente());
            stmt.setDate(3, Date.valueOf(agendamentoConsulta.data()));
            stmt.setString(4, agendamentoConsulta.telefone());
            stmt.setString(5, agendamentoConsulta.email());
            stmt.setString(6, String.valueOf(agendamentoConsulta.especificacaoMedico()));
            stmt.setTime(7, Time.valueOf(agendamentoConsulta.hora()));

            stmt.execute();

        } catch (SQLException e) {
            throw new SQLException("Erro ao processar agendamento consulta", e);
        }
    }

    public void minhaConsulta(MinhaConsultaDTO minhaConsultaDTO) {
        try (Connection conn = dataSource.getConnection();
                CallableStatement stmt = conn
                        .prepareCall("{call proc_t09a_minha_consulta(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}")) {
            int paciente = pacienteRepository.findByCpf(minhaConsultaDTO.pacienteCpf())
                    .orElseThrow(() -> new SQLException("Erro banco de dados"))
                    .getId();

            int medico = medicoRepository.findByCrm(minhaConsultaDTO.medicoCrm())
                    .orElseThrow(() -> new SQLException("Erro banco de dados"))
                    .getId();

            int agendamento = ((AgendamentoConsulta) agendamentoRepository.findByPacienteIdAndDataAndHora(
                    paciente,
                    minhaConsultaDTO.data(),
                    minhaConsultaDTO.hora()).orElseThrow(() -> new SQLException("Agendamento não encontrado")))
                    .getId();

            stmt.setDate(1, Date.valueOf(minhaConsultaDTO.data()));
            stmt.setTime(2, Time.valueOf(minhaConsultaDTO.hora()));
            stmt.setString(3, minhaConsultaDTO.descricao());
            stmt.setString(4, minhaConsultaDTO.resultado());
            stmt.setString(5, "S");
            stmt.setString(6, minhaConsultaDTO.consultaStatus());
            stmt.setInt(7, paciente);
            stmt.setInt(8, medico);
            stmt.setInt(9, agendamento);
            stmt.setString(10, minhaConsultaDTO.frequencia());
            stmt.setString(11, minhaConsultaDTO.pressaoArterial());
            stmt.setString(12, minhaConsultaDTO.temperatura());

            stmt.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void cadastrarPrescricao(PrescricaoDTO prescricaoDTO) throws SQLException {
        try (Connection conn = dataSource.getConnection()) {
            CallableStatement stmt = conn.prepareCall("call proc_t09a_precricao(?,?,?)");

            stmt.setString(1, prescricaoDTO.remedio());
            stmt.setString(2, String.valueOf(prescricaoDTO.data()));
            stmt.setString(3, prescricaoDTO.descricao());

            stmt.execute();

        } catch (SQLException e) {
            throw new SQLException("Erro ao cadastrar prescrição");
        }
    }

    public void cadastrarResultadoConsulta(ResultadoConsultaDTO resultadoConsultaDTO) throws SQLException {
        try (Connection conn = dataSource.getConnection()) {
            CallableStatement stmt = conn.prepareCall("call proc_t09a_resultado_consulta(?,?,?,?,?,?)");

            stmt.setString(1, resultadoConsultaDTO.descricao());
            stmt.setString(2, String.valueOf(resultadoConsultaDTO.dataResultado()));
            stmt.setRowId(3, (RowId) resultadoConsultaDTO.medico());
            stmt.setRowId(4, (RowId) resultadoConsultaDTO.paciente());
            stmt.setRowId(5, (RowId) resultadoConsultaDTO.prescricao());
            stmt.setRowId(6, (RowId) resultadoConsultaDTO.minhaConsulta());

        } catch (SQLException e) {
            throw new SQLException("Erro ao processar resultado exame");
        }
    }

    public List todasConsultas(CpfDTO cpfPaciente) {

        return agendamentoRepository.findAll()
                .stream()
                .filter(e -> e.getPaciente().getCpf().equals(cpfPaciente.cpf()))
                .toList();
    }

    public List listarConsultas(CpfDTO cpfPaciente) {
        return agendamentoRepository.findAll()
                .stream()
                .filter(e -> e.getPaciente().getCpf().equals(cpfPaciente.cpf()))
                .map(RetornoAgendamentoDTO::new)
                .toList();

    }
}
