package com.example.oracleapi.Controller;


import com.example.oracleapi.Entity.AgendamentoConsulta;
import com.example.oracleapi.Service.PacienteService;
import com.example.oracleapi.Exception.ConsultaException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.Map;

@RestController("/paciente")
public class PacienteController {

    @Autowired
    private PacienteService pacienteService;

    @PostMapping("/agendar-consulta")
    public ResponseEntity<?> consulta(@RequestBody AgendamentoConsulta agendamentoConsulta){
        try {
            pacienteService.agendarConsulta(agendamentoConsulta);
            return ResponseEntity.status(200).body(Map.of("mensagem", "Agendamento concluido com sucesso"));
        }catch (ConsultaException | SQLException e){
            throw new ConsultaException("Erro ao cadastrar consulta" + e.getMessage());
        }
    }


}
