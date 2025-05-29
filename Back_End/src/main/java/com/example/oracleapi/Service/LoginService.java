package com.example.oracleapi.Service;

import com.example.oracleapi.DTO.EnderecoDTO;
import com.example.oracleapi.DTO.LoginDTO;
import com.example.oracleapi.Entity.Endereco;
import com.example.oracleapi.Entity.Paciente;
import com.example.oracleapi.Exception.LoginException;
import com.example.oracleapi.Repository.EnderecoRepository;
import com.example.oracleapi.Repository.PacienteRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.multipart.MultipartFile;
import javax.sql.DataSource;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Optional;

@CrossOrigin(origins = "*")
@Service
public class LoginService {

    private final EnderecoRepository enderecoRepository;

    private final PacienteRepository pacienteRepository;

    private final DataSource dataSource;

    public LoginService(DataSource dataSource, PacienteRepository pacienteRepository, EnderecoRepository enderecoRepository) {
        this.dataSource = dataSource;
        this.pacienteRepository = pacienteRepository;
        this.enderecoRepository = enderecoRepository;
    }


    public void cadastrar(Paciente paciente, MultipartFile arquivo) throws SQLException, IOException {
        try (Connection conn = dataSource.getConnection();
                CallableStatement stmt = conn.prepareCall("{call proc_t09a_cadastro_paciente (?,?,?,?,?,?,?,?,?,?)}")) {

            stmt.setString(1, paciente.getEmail());
            stmt.setString(2, paciente.getSenha());
            stmt.setString(3, paciente.getCpf());
            stmt.setString(4, paciente.getSexo());
            stmt.setString(5, paciente.getTelefone());
            stmt.setString(6, paciente.getNome());
            stmt.setDate(7, java.sql.Date.valueOf(paciente.getDataNascimento()));
            stmt.setString(8, "S"); 
            stmt.setDate(9, java.sql.Date.valueOf(LocalDate.now()));
            stmt.setString(10, null);
            stmt.execute();


            if (arquivo != null && !arquivo.isEmpty()) {
                salvarDocumento(paciente.getCpf(), arquivo);
            }
        } catch (SQLException e) {
            throw new SQLException("Erro ao cadastrar paciente: " + e.getMessage(), e);
        }
    }

    public void salvarDocumento(String cpf, MultipartFile arquivo) throws IOException {
        if (arquivo == null || arquivo.isEmpty())
            return;

        Path uploadPath = Paths.get("uploads");
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        String nomeArquivo = cpf + "_" + arquivo.getOriginalFilename();
        Path caminhoArquivo = uploadPath.resolve(nomeArquivo);
        Files.copy(arquivo.getInputStream(), caminhoArquivo, StandardCopyOption.REPLACE_EXISTING);

        Paciente paciente = pacienteRepository.findByCpf(cpf)
                .orElseThrow(() -> new IOException("Paciente não encontrado"));

        paciente.setDocumento(nomeArquivo);
        pacienteRepository.save(paciente);
    }

    public Paciente login(LoginDTO loginDTO) throws SQLException, LoginException {
        try (Connection conn = dataSource.getConnection();
                CallableStatement stmt = conn.prepareCall("{call proc_t09a_login_paciente (?,?)}")) {

            stmt.setString(1, loginDTO.cpf());
            stmt.setString(2, loginDTO.senha());
            stmt.execute();

            return pacienteRepository.findByCpf(loginDTO.cpf())
                    .orElseThrow(() -> new LoginException("Paciente não encontrado após login."));
        } catch (SQLException e) {
            if (e.getErrorCode() == 20001) {
                throw new LoginException("CPF ou senha inválidos.");
            }
            throw new SQLException("Erro ao processar login: " + e.getMessage(), e);
        } catch (LoginException e) {
            throw new LoginException("Erro inesperado: " + e.getMessage());
        }
    }

    public void cadastrarEndereco(EnderecoDTO endereco) throws SQLException {
        try (Connection conn = dataSource.getConnection();
                CallableStatement stmt = conn.prepareCall("{call proc_t09a_endereco(?, ?, ?, ?, ?, ?, ?)}")) {
            stmt.setString(1, endereco.cep());
            stmt.setString(2, endereco.logradouro());
            stmt.setString(3, endereco.cidade());
            stmt.setString(4, endereco.uf());
            stmt.setString(5, endereco.bairro());
            stmt.setString(6, endereco.complemento());
            stmt.setString(7, endereco.numero());

            if (endereco.numero() != null && !endereco.numero().isEmpty()) {
                stmt.setInt(7, Integer.parseInt(endereco.numero()));
            } else {
                stmt.setNull(7, java.sql.Types.INTEGER);
            }
            stmt.execute();

            Optional<Endereco> enderecoSalvo = enderecoRepository.findByCepAndNumeroAndLogradouro(endereco.cep(), endereco.numero(), endereco.logradouro());
            if (enderecoSalvo.isEmpty()) {
                throw new SQLException("Endereço não foi salvo corretamente.");
            }

            Paciente paciente = pacienteRepository.findByCpf(endereco.cpfPaciente())
                    .orElseThrow(() -> new SQLException("Paciente não encontrado"));

            paciente.setEndereco(enderecoSalvo.get());
            pacienteRepository.save(paciente);

        } catch (SQLException e) {
            throw new SQLException("Erro com a ligação do banco: " + e.getMessage());
        }
    }
}
