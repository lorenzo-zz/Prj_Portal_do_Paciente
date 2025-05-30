package com.example.oracleapi.Service;

import com.example.oracleapi.DTO.*;
import com.example.oracleapi.Entity.Paciente;
import com.example.oracleapi.Exception.AlergiaException;
import com.example.oracleapi.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.sql.DataSource;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*")
@RequestMapping("/paciente")
@Service
public class PacienteService {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private PacienteRepository pacienteRepository;

    public void cadastrarAlergia(PacienteAlergiaDTO pacienteAlergiaDTO) throws SQLException {

        try (Connection conn = dataSource.getConnection()) {
            CallableStatement stmt = conn.prepareCall("{call proc_t09a_paciente_alergia(?, ?)}");

            int paciente = pacienteRepository.findByCpf(pacienteAlergiaDTO.pacienteCpf())
                    .orElseThrow(() -> new SQLException("Erro banco de dados"))
                    .getId();

            stmt.setString(1, pacienteAlergiaDTO.nomeAlergia());
            stmt.setInt(2, paciente);

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

    public void atualziarDadosPaciente(AtualizarPacienteDTO atualizarPacienteDTO) {
        try (Connection conn = dataSource.getConnection();
                CallableStatement stmt = conn
                        .prepareCall("{call proc_t09a_atualizar_dados_paciente(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}")) {
            LocalDate dataNascimento = pacienteRepository.findByCpf(atualizarPacienteDTO.cpf())
                    .map(Paciente::getDataNascimento).orElseThrow(() -> new RuntimeException(
                            "Paciente não encontrado com CPF: " + atualizarPacienteDTO.cpf()));
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

    public void atualizarDadosPaciente(AtualizarPacienteDTO atualizarPacienteDTO) {
        try (Connection conn = dataSource.getConnection();
                CallableStatement stmt = conn.prepareCall(
                        "{call proc_t09a_atualizar_dados_paciente(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}")) {

            // Busca a data de nascimento já cadastrada
            String dataNascimento = pacienteRepository.findByCpf(atualizarPacienteDTO.cpf())
                    .map(p -> p.getDataNascimento() != null ? p.getDataNascimento().toString() : null)
                    .orElse(null);

            // Mapeamento dos parâmetros na ordem correta
            stmt.setString(1, atualizarPacienteDTO.cpf());
            stmt.setString(2, atualizarPacienteDTO.nomeCompleto());
            stmt.setString(3, atualizarPacienteDTO.telefone());
            stmt.setString(4, atualizarPacienteDTO.email());
            stmt.setString(5, dataNascimento); // Envia como VARCHAR no formato 'YYYY-MM-DD'
            stmt.setString(6, atualizarPacienteDTO.cep());
            stmt.setString(7, atualizarPacienteDTO.logradouro());
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
                    cpf,
                    stmt.getString(2),
                    stmt.getString(3),
                    stmt.getString(4),
                    stmt.getDate(5) != null ? String.valueOf(stmt.getDate(5).toLocalDate()) : null, // dataNascimento
                    stmt.getString(6),
                    stmt.getString(7),
                    stmt.getString(8),
                    stmt.getString(9),
                    stmt.getString(10),
                    stmt.getString(11),
                    stmt.getString(12));
        } catch (SQLException e) {
            throw new SQLException("Erro generico" + e.getMessage());
        }
    }

    public List<String> listarAlergiasPorCpf(String cpf) throws SQLException {
        List<String> alergias = new ArrayList<>();

        int pacienteId = pacienteRepository.findByCpf(cpf)
                .orElseThrow(() -> new SQLException("Paciente não encontrado"))
                .getId();

        try (Connection conn = dataSource.getConnection();
                PreparedStatement stmt = conn
                        .prepareStatement("SELECT NOME FROM T09A_PACIENTE_ALERGIA WHERE PACIENTE_ID = ?")) {

            stmt.setInt(1, pacienteId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                alergias.add(rs.getString("nome"));
            }

        } catch (SQLException e) {
            throw new SQLException("Erro ao buscar alergias: " + e.getMessage(), e);
        }

        return alergias;
    }

}
