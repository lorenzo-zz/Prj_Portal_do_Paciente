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
import com.example.oracleapi.DTO.idMinhaConsultaDTO;
import com.example.oracleapi.Entity.AgendamentoConsulta;
import com.example.oracleapi.Entity.MinhaConsulta;
import com.example.oracleapi.Model.ConsultaStatus;
import com.example.oracleapi.Repository.AgendamentoRepository;
import com.example.oracleapi.Repository.MinhaConsultaRepository;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ConsultaService {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private AgendamentoRepository agendamentoRepository;

    @Autowired
    private MinhaConsultaRepository consultaRepository;

    public void agendarConsulta(AgendamentoConsultaDTO agendamentoConsulta) throws SQLException {
        try (Connection conn = dataSource.getConnection()) {
            conn.setAutoCommit(false); // controle manual da transação

            // 1. Chamar procedure de agendamento, supondo que ela retorne ID via OUT param
            CallableStatement stmtAgendamento = conn
                    .prepareCall("{call proc_t09a_agendamento_consulta(?, ?, ?, ?, ?, ?, ?, ? )}");

            stmtAgendamento.setString(1, agendamentoConsulta.cpfPaciente());
            stmtAgendamento.setDate(2, Date.valueOf(agendamentoConsulta.data()));
            stmtAgendamento.setString(3, agendamentoConsulta.telefone());
            stmtAgendamento.setString(4, agendamentoConsulta.email());
            stmtAgendamento.setString(5, String.valueOf(agendamentoConsulta.especificacaoMedico()));
            stmtAgendamento.setTime(6, Time.valueOf(agendamentoConsulta.hora()));
            stmtAgendamento.setString(7, ConsultaStatus.CONFIRMADA.name());
            // Parâmetro OUT para capturar o ID criado
            stmtAgendamento.registerOutParameter(8, java.sql.Types.INTEGER);

            stmtAgendamento.execute();

            int novoAgendamentoId = stmtAgendamento.getInt(8);

            // 2. Chamar procedure para inserir em minha consulta
            CallableStatement stmtMinhaConsulta = conn.prepareCall("{call proc_t09a_minha_consulta(?)}");
            stmtMinhaConsulta.setInt(1, novoAgendamentoId);
            stmtMinhaConsulta.execute();

            conn.commit();

        } catch (SQLException e) {
            throw new SQLException("Erro ao processar agendamento e inclusão em minhas consultas", e);
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

    public List<RetornoAgendamentoDTO> listarConsultas(CpfDTO cpfPaciente) {
        return agendamentoRepository.findAll()
                .stream()
                .filter(e -> e.getPaciente().getCpf().equals(cpfPaciente.cpf()))
                .map(agendamento -> new RetornoAgendamentoDTO(
                        agendamento.getId(),
                        agendamento.getPaciente().getNome(),
                        agendamento.getData(),
                        agendamento.getHora(),
                        agendamento.getEspecificacaoMedico(),
                        agendamento.getStatus()))
                .toList();

    }

    public List<MinhaConsulta> dadosConsulta(MinhaConsultaDTO dto) {
        return consultaRepository.findByAgendamentoConsultaId(dto.idAgendamento());
    }

    @Transactional
    public void cancelarConsulta(idMinhaConsultaDTO idConsulta) {
        MinhaConsulta consulta = consultaRepository.findById(idConsulta.idMinhaConsulta())
                .orElseThrow(() -> new RuntimeException("Consulta não encontrada"));

        AgendamentoConsulta agendamento = agendamentoRepository.findById(idConsulta.idAgendamento())
                .orElseThrow(() -> new RuntimeException("Agendamento não encontrado"));

        agendamento.setStatus(ConsultaStatus.CANCELADA_PELO_PACIENTE);
        agendamentoRepository.save(agendamento);

        consulta.setConsultaStatus(ConsultaStatus.CANCELADA_PELO_PACIENTE);
        consultaRepository.save(consulta);
    }
}
