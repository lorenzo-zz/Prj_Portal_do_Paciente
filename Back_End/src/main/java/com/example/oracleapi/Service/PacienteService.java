package com.example.oracleapi.Service;

import com.example.oracleapi.DTO.*;
import com.example.oracleapi.Entity.AgendamentoConsulta;
import com.example.oracleapi.Entity.Paciente;
import com.example.oracleapi.Exception.AlergiaException;
import com.example.oracleapi.Repository.AgendamentoRepository;
import com.example.oracleapi.Repository.MedicoRepository;
import com.example.oracleapi.Repository.MinhaConsultaRepository;
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

    @Autowired
    private AgendamentoRepository agendamentoRepository;

    @Autowired
    private MinhaConsultaRepository minhaConsultaRepository;

    @Autowired
    private MedicoRepository medicoRepository;

    // @Autowired
    // private AlergiaRepository alergiaRepository;

    public void agendarConsulta(AgendamentoConsultaDTO agendamentoConsulta) throws SQLException {
        try (Connection conn = dataSource.getConnection()) {
            CallableStatement stmt = conn.prepareCall("{call proc_t09a_agendamento_consulta(?, ?, ?, ?, ?)}");

            int paciente = pacienteRepository.findByCpf(agendamentoConsulta.pacienteCpf())
                    .orElseThrow(() -> new SQLException("Erro banco de dados"))
                    .getId();

            stmt.setString(1, agendamentoConsulta.descricao());
            stmt.setDate(2, Date.valueOf(agendamentoConsulta.data())); // java.time.LocalDate
            stmt.setTime(3, Time.valueOf(agendamentoConsulta.hora())); // java.time.LocalTime
            stmt.setInt(4, paciente);
            stmt.setString(5, String.valueOf(agendamentoConsulta.especificacaoMedico()));

            stmt.execute();

        } catch (SQLException e) {
            throw new SQLException("Erro ao processar gendamento consulta");
        }
    }

    public void cadastrarMedico(MedicoDTO medicoDTO) throws SQLException {
        try (Connection conn = dataSource.getConnection()) {
            CallableStatement stmt = conn.prepareCall("call proc_t09a_medico(?,?,?,?)");

            stmt.setString(1, medicoDTO.nome());
            stmt.setString(2, "S");
            stmt.setString(3, String.valueOf(medicoDTO.especificacaoMedico()));
            stmt.setString(4, medicoDTO.crm());

            stmt.execute();

        } catch (SQLException e) {
            throw new SQLException("Erro ao processar cadastro medico");

        }

    }

    public void minhaConsulta(MinhaConsultaDTO minhaConsultaDTO) {
        try (Connection conn = dataSource.getConnection();
                CallableStatement stmt = conn
                        .prepareCall("{call proc_t09a_agendamento_consulta(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}")) {
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

    public void cadastrarRequisicaoExame(RequisicaoExameDTO requisicaoExameDTO) throws SQLException {
        try (Connection conn = dataSource.getConnection()) {
            CallableStatement stmt = conn.prepareCall("call proc_t09a_requisicao_exame(?,?,?,?,?,?,?)");

            int paciente = pacienteRepository.findByCpf(requisicaoExameDTO.pacienteCpf())
                    .orElseThrow(() -> new SQLException("Paciente não encontrado"))
                    .getId();

            // Buscar médico pelo CRM
            int medico = medicoRepository.findByCrm(requisicaoExameDTO.medicoCrm())
                    .orElseThrow(() -> new SQLException("Médico não encontrado"))
                    .getId();

            // Buscar ID da MinhaConsulta
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

    public RetornoPacienteDTO dadosDoPaciente(String cpf) throws SQLException {
        try (Connection conn = dataSource.getConnection()) {
            CallableStatement stmt = conn
                    .prepareCall("{call proc_t09a_dados_paciente(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}");

            stmt.setString(1, cpf);

            // Registrar os parâmetros OUT
            stmt.registerOutParameter(2, Types.VARCHAR); // nome
            stmt.registerOutParameter(3, Types.VARCHAR); // telefone
            stmt.registerOutParameter(4, Types.VARCHAR); // email
            stmt.registerOutParameter(5, Types.DATE); // dataNascimento
            stmt.registerOutParameter(6, Types.VARCHAR); // cep
            stmt.registerOutParameter(7, Types.VARCHAR); // logradouro
            stmt.registerOutParameter(8, Types.VARCHAR); // numero
            stmt.registerOutParameter(9, Types.VARCHAR); // complemento
            stmt.registerOutParameter(10, Types.VARCHAR); // bairro
            stmt.registerOutParameter(11, Types.VARCHAR); // cidade
            stmt.registerOutParameter(12, Types.VARCHAR); // uf

            // Executar
            stmt.execute();

            // Montar DTO de retorno
            return new RetornoPacienteDTO(
                    stmt.getString(2), // nome
                    stmt.getString(3), // telefone
                    stmt.getString(4), // email
                    stmt.getDate(5) != null ? String.valueOf(stmt.getDate(5).toLocalDate()) : null, // dataNascimento
                    stmt.getString(6), // cep
                    stmt.getString(7), // logradouro
                    stmt.getString(8), // numero
                    stmt.getString(9), // complemento
                    stmt.getString(10), // bairro
                    stmt.getString(11), // cidade
                    stmt.getString(12), // uf
                    cpf // cpf (último!)
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
