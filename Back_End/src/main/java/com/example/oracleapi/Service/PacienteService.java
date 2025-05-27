package com.example.oracleapi.Service;

import com.example.oracleapi.DTO.*;
import com.example.oracleapi.Entity.Paciente;
import com.example.oracleapi.Exception.AlergiaException;
import com.example.oracleapi.Repository.*;
import com.example.oracleapi.Repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.sql.DataSource;
import java.sql.*;
import java.time.LocalDate;

@CrossOrigin(origins = "*")
@RequestMapping("/paciente")
@Service
public class PacienteService {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private MinhaConsultaRepository minhaConsultaRepository;

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private PrescricaoRepository prescricaoRepository;

    public void agendarConsulta(AgendamentoConsultaDTO agendamentoConsulta) throws SQLException {
        try (Connection conn = dataSource.getConnection()) {
            CallableStatement stmt = conn.prepareCall("{call proc_t09a_agendamento_consulta(?, ?, ?, ?, ?)}");

            int paciente = pacienteRepository.findByCpf(agendamentoConsulta.cpfPaciente())
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



    public void cadastrarAlergia(PacienteAlergiaDTO pacienteAlergiaDTO) throws SQLException {
        Paciente pacienteExistente = pacienteRepository.findByCpf(pacienteAlergiaDTO.paciente().getCpf())
                .orElseThrow(() -> new SQLException("Paciente não encontrado"));

        try (Connection conn = dataSource.getConnection()) {
            CallableStatement stmt = conn.prepareCall("{call proc_t09a_paciente_alergia(?, ?)}");


            int paciente = pacienteRepository.findByCpf(pacienteAlergiaDTO.pacienteCpf())
                    .orElseThrow(() -> new SQLException("Erro banco de dados"))
                    .getId();

            stmt.setString(1, pacienteAlergiaDTO.descricao());
            stmt.setString(2, pacienteAlergiaDTO.nomeAlergia());
            stmt.setInt(3, paciente);

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


            int minhaConsultaId = requisicaoExameDTO.minhaConsulta().getId();

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

            stmt.setInt(5, minhaConsultaId);

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

            int pacienteId = pacienteRepository.findByCpf(resultadoConsultaDTO.pacienteCpf())
                    .orElseThrow(() -> new SQLException("Paciente não encontrado"))
                    .getId();

            int medicoId = medicoRepository.findByCrm(resultadoConsultaDTO.medicoCrm())
                    .orElseThrow(() -> new SQLException("Médico não encontrado"))
                    .getId();

            int minhaConsultaId = minhaConsultaRepository.findByPacienteAndMedicoAndDataAndHora(
                            pacienteId,
                            medicoId,
                            resultadoConsultaDTO.dataResultado(),
                            resultadoConsultaDTO.hora()
                    ).orElseThrow(() -> new SQLException("Consulta não encontrada"))
                    .getId();

            int prescricaoId = prescricaoRepository.findByDescricao(resultadoConsultaDTO.prescricaoDescricao())
                    .orElseThrow(() -> new SQLException("Prescrição não encontrada"))
                    .getId();


            stmt.setString(1, resultadoConsultaDTO.descricao());
            stmt.setString(2, String.valueOf(resultadoConsultaDTO.dataResultado()));
            stmt.setInt(3, medicoId);
            stmt.setInt(4, pacienteId);
            stmt.setInt(5, prescricaoId);
            stmt.setInt(6, minhaConsultaId);

            stmt.execute();

        } catch (SQLException e) {
            throw new SQLException("Erro ao processar resultado exame");
        }
    }

    public void cadastrarResultadoExame(ResultadoExameDTO resultadoExameDTO) {
        try (Connection conn = dataSource.getConnection();
             CallableStatement stmt = conn.prepareCall("{call proc_t09a_resultado_exame(?, ?)}");) {

            int requisicaoExameId = resultadoExameDTO.requisicaoExame().getId();

            stmt.setString(1, resultadoExameDTO.descricao());
            stmt.setInt(2, requisicaoExameId);

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


    public void atualziarDadosPaciente(AtualizarPacienteDTO atualizarPacienteDTO) {
        try (Connection conn = dataSource.getConnection();
             CallableStatement stmt = conn.prepareCall(
                     "{call proc_t09a_atualizar_dados_paciente(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}")) {

            LocalDate dataNascimento = pacienteRepository.findByCpf(atualizarPacienteDTO.cpf())
                    .map(Paciente::getDataNascimento)
                    .orElseThrow(() -> new RuntimeException("Paciente não encontrado com CPF: " + atualizarPacienteDTO.cpf()));


            stmt.setString(1, atualizarPacienteDTO.cpf());
            stmt.setString(2, atualizarPacienteDTO.nomeCompleto());
            stmt.setString(3, atualizarPacienteDTO.telefone());
            stmt.setString(4, atualizarPacienteDTO.email());
            stmt.setString(5, atualizarPacienteDTO.cep());
            stmt.setString(6, atualizarPacienteDTO.logradouro());
            stmt.setString(7, String.valueOf(dataNascimento));
            stmt.setString(8, atualizarPacienteDTO.numero());
            stmt.setString(9, atualizarPacienteDTO.complemento());
            stmt.setString(10, atualizarPacienteDTO.bairro());
            stmt.setString(11, atualizarPacienteDTO.cidade());
            stmt.setString(12, atualizarPacienteDTO.uf());


            stmt.execute();


        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao atualizar dados do paciente: " + e.getMessage());
        }


    }
}
