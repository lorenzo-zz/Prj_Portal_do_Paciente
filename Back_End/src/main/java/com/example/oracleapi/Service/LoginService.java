package com.example.oracleapi.Service;

import com.example.oracleapi.DTO.LoginDTO;
import com.example.oracleapi.Entity.Paciente;
import com.example.oracleapi.Exception.LoginException;
import com.example.oracleapi.Repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

@CrossOrigin(origins = "*")
@Service
public class LoginService {

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private DataSource dataSource;

    // Metodo de cadastrar paciente

    public void cadastrar(Paciente paciente, MultipartFile arquivo) throws SQLException, IOException {
        try (Connection conn = dataSource.getConnection();
                CallableStatement stmt = conn.prepareCall("{call proc_t09a_cadastro_paciente (?,?,?,?,?,?,?,?,?)}")) {

            stmt.setString(1, paciente.getEmail());
            stmt.setString(2, paciente.getSenha());
            stmt.setString(3, paciente.getCpf());
            stmt.setString(4, paciente.getSexo());
            stmt.setString(5, paciente.getTelefone());
            stmt.setString(6, paciente.getNome());
            stmt.setDate(7, java.sql.Date.valueOf(paciente.getDataNascimento()));
            stmt.setString(8, "S"); // Sempre "S" para ativo
            stmt.setDate(9, java.sql.Date.valueOf(LocalDate.now()));

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

    public void login(LoginDTO loginDTO) throws SQLException, LoginException {
        try (Connection conn = dataSource.getConnection();
                CallableStatement stmt = conn.prepareCall("{call proc_t09a_login_paciente (?,?)}")) {

            stmt.setString(1, loginDTO.cpf());
            stmt.setString(2, loginDTO.senha());
            stmt.execute();

        } catch (SQLException e) {
            throw new SQLException("Erro com a ligação do banco: " + e.getMessage(), e);
        }
    }

}
