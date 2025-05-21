package com.example.oracleapi.Service;

import com.example.oracleapi.DTO.LoginDTO;
import com.example.oracleapi.Entity.Paciente;
import com.example.oracleapi.Exception.LoginException;
import com.example.oracleapi.Repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
<<<<<<< HEAD
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.security.auth.login.LoginException;
import java.sql.*;
=======
import org.springframework.web.multipart.MultipartFile;

>>>>>>> d2f90055c0c2e5a918f8cd89977e511adc77c595
import javax.sql.DataSource;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

@CrossOrigin(origins = "*")
@Service
public class LoginService {

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private DataSource dataSource;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // Metodo de cadastrar paciente

    public void cadastrar(Paciente paciente) throws SQLException {
        try (Connection conn = dataSource.getConnection();
             CallableStatement stmt = conn.prepareCall("{call proc_t09a_cadastro_paciente (?,?,?,?,?,?,?,?)}") ){

            stmt.setString(1, paciente.getEmail());
            stmt.setString(2, paciente.getSenha());
            stmt.setString(3, paciente.getCpf());
            stmt.setString(4, String.valueOf(paciente.getSexo()));
            stmt.setString(5, paciente.getTelefone());
            stmt.setString(6, paciente.getNome());
<<<<<<< HEAD
            stmt.setString(7, "S"); 
            stmt.setNull(8, java.sql.Types.DATE); 
            stmt.setDate(9, java.sql.Date.valueOf(paciente.getDataNascimento()));
=======
            stmt.setString(7, null); // ativo
>>>>>>> d2f90055c0c2e5a918f8cd89977e511adc77c595

            stmt.execute();
        } catch (SQLException e) {
            throw new SQLException("Erro ao cadastrar paciente: " + e.getMessage(), e);
        }
    }

    //Metodo de Salvar documento do paciente

    public void salvarDocumento(String cpf, MultipartFile arquivo) throws IOException {
        if (arquivo == null || arquivo.isEmpty()) return;

        Path uploadPath = Paths.get("uploads");
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        String nomeArquivo = cpf + "_" + arquivo.getOriginalFilename();
        Path caminhoArquivo = uploadPath.resolve(nomeArquivo);
        Files.copy(arquivo.getInputStream(), caminhoArquivo, StandardCopyOption.REPLACE_EXISTING);

        // Atualiza apenas o caminho do documento no paciente
        pacienteRepository.atualizarCaminhoDocumento(cpf, caminhoArquivo.toString());

    }

    // Metodo de Login

    public void login(LoginDTO loginDTO) throws SQLException, LoginException {
        try (Connection conn = dataSource.getConnection();
             CallableStatement stmt = conn.prepareCall("{call proc_t09a_login_paciente (?,?)}")) {

            stmt.setString(1, loginDTO.nome());
            stmt.setString(2, loginDTO.senha());
            stmt.execute();

        } catch (SQLException e) {
            throw new SQLException("Erro com a ligação do banco: " + e.getMessage(), e);
        }
    }

}
