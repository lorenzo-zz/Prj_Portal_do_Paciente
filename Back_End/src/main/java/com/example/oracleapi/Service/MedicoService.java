package com.example.oracleapi.Service;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.oracleapi.DTO.MedicoDTO;

@Service
public class MedicoService {

    @Autowired
    private DataSource dataSource;

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
}
