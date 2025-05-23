package com.example.oracleapi.Service;

import com.example.oracleapi.DTO.*;
import com.example.oracleapi.Repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.sql.DataSource;
import java.sql.*;

@CrossOrigin(origins = "*")
@Service
public class PacienteService {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private PacienteRepository pacienteRepository;

//    @Autowired
//    private EnderecoRepository enderecoRepository;
//
//    @Autowired
//    private MinhaConsultaRepository minhaConsultaRepository;
//
//    @Autowired
//    private MedicoRepository medicoRepository;
//
//    @Autowired
//    private AlergiaRepository alergiaRepository;


    public void agendarConsulta(AgendamentoConsultaDTO agendamentoConsulta) throws SQLException {
        try (Connection conn = dataSource.getConnection()) {
            CallableStatement stmt = conn.prepareCall("{call proc_t09a_agendamento_consulta(?, ?, ?, ?, ?)}");

            stmt.setString(1, agendamentoConsulta.descricao());
            stmt.setDate(2, Date.valueOf(agendamentoConsulta.data())); // java.time.LocalDate
            stmt.setTime(3, Time.valueOf(agendamentoConsulta.hora())); // java.time.LocalTime
            stmt.setRowId(4, (RowId) agendamentoConsulta.paciente());
            stmt.setString(5, String.valueOf(agendamentoConsulta.especificacaoMedico()));

            stmt.execute();

        } catch (SQLException e) {
            throw new SQLException("Erro ao processar agendamento consulta");
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

    public void minhaConsulta(MinhaConsultaDTO minhaConsultaDTO){
        try (Connection conn = dataSource.getConnection();
             CallableStatement stmt = conn.prepareCall("{call proc_t09a_agendamento_consulta(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}")
        ) {

            stmt.setDate(1, Date.valueOf(minhaConsultaDTO.data()));
            stmt.setTime(2, Time.valueOf(minhaConsultaDTO.hora()));
            stmt.setString(3, minhaConsultaDTO.descricao());
            stmt.setString(4, minhaConsultaDTO.resultado());
            stmt.setString(5, "S");
            stmt.setString(6, minhaConsultaDTO.consultaStatus());
            stmt.setRowId(7, (RowId) minhaConsultaDTO.paciente());
            stmt.setRowId(8, (RowId) minhaConsultaDTO.medico());
            stmt.setRowId(9, (RowId) minhaConsultaDTO.agendamentoConsulta());
            stmt.setString(10, minhaConsultaDTO.frequencia());
            stmt.setString(11,minhaConsultaDTO.pressaoArterial());
            stmt.setString(12, minhaConsultaDTO.temperatura());

            stmt.execute();

        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public void cadastrarAlergia(PacienteAlergiaDTO pacienteAlergiaDTO) throws SQLException {
        try (Connection conn = dataSource.getConnection()) {
            CallableStatement stmt = conn.prepareCall("{call proc_t09a_paciente_alergia(?, ?, ?)}");

            stmt.setString(1, pacienteAlergiaDTO.descricao());
            stmt.setString(2, pacienteAlergiaDTO.nomeAlergia());
            stmt.setRowId(3, (RowId) pacienteAlergiaDTO.paciente());

            stmt.execute();

        } catch (SQLException e) {
            throw new SQLException("Erro ao processar cadastrar alergia");
        }
    }

    public void cadastrarPrescricao(PrescricaoDTO prescricaoDTO) throws SQLException {
        try(Connection conn = dataSource.getConnection()){
            CallableStatement stmt = conn.prepareCall("call proc_t09a_precricao(?,?,?)");

            stmt.setString(1, prescricaoDTO.remedio());
            stmt.setString(2, String.valueOf(prescricaoDTO.data()));
            stmt.setString(3, prescricaoDTO.descricao());

            stmt.execute();

        }catch (SQLException e){
            throw  new SQLException("Erro ao cadastrar prescrição");
        }
    }


    public void cadastrarRequisicaoExame(RequisicaoExameDTO requisicaoExameDTO) throws SQLException {
        try (Connection conn = dataSource.getConnection()){
            CallableStatement stmt = conn.prepareCall("call proc_t09a_requisicao_exame(?,?,?,?,?,?,?)");

            stmt.setString(1, String.valueOf(requisicaoExameDTO.dataRequisicao()));
            stmt.setString(2, String.valueOf(requisicaoExameDTO.horaRequisicao()));
            stmt.setString(3, requisicaoExameDTO.descricao());
            stmt.setString(4, requisicaoExameDTO.tipoExame());
            stmt.setRowId(5, (RowId) requisicaoExameDTO.minhaConsulta());
            stmt.setString(6, requisicaoExameDTO.tipoExame());
            stmt.setString(7, requisicaoExameDTO.nomeDocumento());

            stmt.execute();

        } catch (SQLException e) {
            throw new SQLException("Erro ao processar a requisição do exame");
        }
    }


    public void cadastrarResultadoConsulta(ResultadoConsultaDTO resultadoConsultaDTO) throws SQLException {
        try(Connection conn = dataSource.getConnection()){
            CallableStatement stmt = conn.prepareCall("call proc_t09a_resultado_consulta(?,?,?,?,?,?)");

            stmt.setString(1,resultadoConsultaDTO.descricao());
            stmt.setString(2, String.valueOf(resultadoConsultaDTO.dataResultado()));
            stmt.setRowId(3, (RowId) resultadoConsultaDTO.medico());
            stmt.setRowId(4, (RowId) resultadoConsultaDTO.paciente());
            stmt.setRowId(5, (RowId) resultadoConsultaDTO.prescricao());
            stmt.setRowId(6, (RowId) resultadoConsultaDTO.minhaConsulta());

        }catch (SQLException e){
            throw new SQLException("Erro ao processar resultado exame");
        }
    }


    public void cadastrarResultadoExame(ResultadoExameDTO resultadoExameDTO) {
        try (Connection conn = dataSource.getConnection();
             CallableStatement stmt = conn.prepareCall("{call proc_t09a_resultado_exame(?, ?)}");
        ) {

            stmt.setString(1, String.valueOf(Date.valueOf(resultadoExameDTO.descricao())));
            stmt.setRowId(2, (RowId) resultadoExameDTO.requisicaoExame());

            stmt.execute();

        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public void dadosDoPaciente(RetornoPacienteDTO retornoPacienteDTO) throws SQLException {
        try (Connection conn = dataSource.getConnection()) {
            CallableStatement stmt = conn.prepareCall("{call proc_t09a_dados_paciente(?)}");

            stmt.setString(1, retornoPacienteDTO.cpf());

            stmt.execute();

        }catch (SQLException e){
            throw new SQLException("Erro generico" + e.getMessage());
        }
    }
}
