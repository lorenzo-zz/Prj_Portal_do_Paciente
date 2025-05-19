package com.example.oracleapi.Service;




import javax.security.auth.login.LoginException;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.oracleapi.Entity.Paciente;



@Service
public class LoginService {


    @Autowired
    DataSource dataSource;

    public void cadastrar(Paciente paciente) throws SQLException {
        try (Connection conn = dataSource.getConnection();
             CallableStatement stmt = conn.prepareCall("{call proc_t09a_cadastro_paciente (?,?,?,?,?,?,?,?,?)}")) {

            stmt.setString(1, paciente.getEmail());
            stmt.setString(2, paciente.getSenha());
            stmt.setString(3, paciente.getCpf());
            stmt.setString(4, String.valueOf(paciente.getSexo())); // CHAR
            stmt.setString(5, paciente.getTelefone());
            stmt.setString(6, paciente.getNome());
            stmt.setString(7, String.valueOf(paciente.getAtivo())); // CHAR

            // Data cadastro (parâmetro 8)
            if (paciente.getDataCadastro() != null) {
                stmt.setDate(8, java.sql.Date.valueOf(paciente.getDataCadastro()));
            } else {
                stmt.setNull(8, java.sql.Types.DATE);
            }

            // Data nascimento (parâmetro 9)
            if (paciente.getDataNascimento() != null) {
                stmt.setDate(9, java.sql.Date.valueOf(paciente.getDataNascimento()));
            } else {
                stmt.setNull(9, java.sql.Types.DATE);
            }

            stmt.execute();
        } catch (SQLException e) {
            throw new SQLException("Erro ao cadastrar paciente: " + e.getMessage(), e);
        }
    }

    public Paciente login(Paciente paciente) throws SQLException, LoginException {
        try {
            Connection conn = dataSource.getConnection();
            CallableStatement stmt = conn.prepareCall("{call proc_t09a_login_paciente (?,?,?)}");
            stmt.setString(1, paciente.getEmail());
            stmt.setString(2, paciente.getSenha());
            stmt.registerOutParameter(3, java.sql.Types.INTEGER);
            stmt.execute();
            int id = stmt.getInt(3);
            stmt.close();
            if (id == 0) {
                throw new LoginException("Usuário ou senha inválidos");
            }
            return paciente;
        } catch (SQLException e) {
            throw new SQLException("Erro com a ligação do banco!");
        }
    }
}

