package com.example.oracleapi.Service;


import com.example.oracleapi.DTO.LoginDTO;
import com.example.oracleapi.Entity.Paciente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.security.auth.login.LoginException;
import javax.sql.DataSource;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;



@Service
public class LoginService {


    @Autowired
    DataSource dataSource;

    public void cadastrar(Paciente paciente) throws SQLException {
        try (Connection conn = dataSource.getConnection();
             CallableStatement stmt = conn.prepareCall("{call proc_t09a_cadastro_paciente(?, ?, ?, ?, ?, ?, ?)}")){

             stmt.setString(1, paciente.getEmail());
             stmt.setString(2, paciente.getSenha());
             stmt.setString(3, paciente.getCpf());
             stmt.setString(4, String.valueOf(paciente.getSexo())); // CHAR
             stmt.setString(5, paciente.getTelefone());
             stmt.setString(6, paciente.getNome());
             stmt.setDate(7, java.sql.Date.valueOf(paciente.getDataNascimento()));

            stmt.execute();
        } catch (SQLException e) {
            throw new SQLException("Erro ao cadastrar paciente: " + e.getMessage(), e);
        }
    }

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

