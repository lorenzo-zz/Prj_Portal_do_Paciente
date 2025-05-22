package com.example.oracleapi.Service;

import com.example.oracleapi.Entity.AgendamentoConsulta;
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

    public void agendarConsulta(AgendamentoConsulta agendamentoConsulta) throws SQLException {
        try (Connection conn = dataSource.getConnection();
             CallableStatement stmt = conn.prepareCall("{call proc_t09a_agendamento_consulta(?, ?, ?, ?, ?)}")
        ) {
            stmt.setString(1, agendamentoConsulta.getDescricao());
            stmt.setDate(2, Date.valueOf(agendamentoConsulta.getData())); // java.time.LocalDate
            stmt.setTime(3, Time.valueOf(agendamentoConsulta.getHora())); // java.time.LocalTime
            stmt.setRowId(4, (RowId) agendamentoConsulta.getPaciente());
            stmt.setString(5, String.valueOf(agendamentoConsulta.getEspecificacaoMedico()));

            stmt.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
