package com.example.oracleapi.Service;

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
    private DataSource dataSource;
    public void cadastrar(Paciente paciente) throws SQLException {
        
        try{
            Connection conn = dataSource.getConnection();
            CallableStatement stmt = conn.prepareCall("{call proc_t09a_cadastro_paciente (?,?,?,?,?,?,?,?,?)}");
            stmt.setString(1, paciente.getNome());
            stmt.setString(2, paciente.getCpf());
            stmt.setString(3, paciente.getEmail());
            stmt.setString(4, paciente.getSenha());
            stmt.setString(5, String.valueOf(paciente.getSexo()));
            stmt.setString(6, paciente.getTelefone());
            stmt.setString(7, String.valueOf(paciente.getDataNascimento()));
            stmt.setString(8, String.valueOf(paciente.getAtivo()));
            stmt.setString(9, String.valueOf(paciente.getDataCadastro()));
            stmt.execute();
            stmt.close();
        }catch(SQLException e){
            throw new SQLException("Erro ao cadastrar paciente: " + e.getMessage());
        }
    }
}
