package com.example.oracleapi.Controller;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import com.example.oracleapi.DTO.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.oracleapi.Entity.MinhaConsulta;
import com.example.oracleapi.Exception.ConsultaException;
import com.example.oracleapi.Exception.PrescricaoException;
import com.example.oracleapi.Exception.ResultadoConsultaExeception;
import com.example.oracleapi.Service.ConsultaService;

@RestController
@RequestMapping("/consulta")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ConsultaController {

    @Autowired
    private ConsultaService consultaService;

    @PostMapping("/agendar-consulta")
    public ResponseEntity<?> consulta(@RequestBody AgendamentoConsultaDTO agendamentoConsultaDTO) throws SQLException {
        try {
            consultaService.agendarConsulta(agendamentoConsultaDTO);
            return ResponseEntity.status(200).body(Map.of("mensagem", "Agendamento concluido com sucesso"));
        } catch (SQLException e) {
            System.err.println("Erro Oracle Code: " + e.getErrorCode());
            System.err.println("Mensagem do Oracle: " + e.getMessage());
            throw new SQLException("Erro ao processar agendamento consulta: " + e.getMessage(), e);
        }
    }

    @PostMapping("/prescricao")
    public ResponseEntity<?> prescricao(@RequestBody PrescricaoDTO prescricaoDTO) {
        try {
            consultaService.cadastrarPrescricao(prescricaoDTO);
            return ResponseEntity.status(200).body(Map.of("Messagem", "Prescrição cadastrado com sucesso"));
        } catch (PrescricaoException e) {
            throw new PrescricaoException("Erro ao cadastrar o prescrição" + e.getMessage());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/resultado-consulta")
    public ResponseEntity<?> resultadoConsulta(@RequestBody ResultadoConsultaDTO resultadoExameDTO) {
        try {
            consultaService.cadastrarResultadoConsulta(resultadoExameDTO);
            return ResponseEntity.status(200).body(Map.of("Messagem", "Resultado da consulta salvo com sucesso"));
        } catch (ResultadoConsultaExeception | SQLException e) {
            throw new ResultadoConsultaExeception("Erro ao salvar o resultado do exame" + e.getMessage());
        }
    }

    @PostMapping("/listar-consultas")
    public ResponseEntity<List<RetornoAgendamentoDTO>> listarConsultas(@RequestBody CpfDTO cpfPaciente) {
        try {
            List<RetornoAgendamentoDTO> consultas = consultaService.listarConsultas(cpfPaciente);
            return ResponseEntity.ok(consultas);
        } catch (ConsultaException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Collections.emptyList());
        }
    }

    @PostMapping("/dados-consulta")
    public ResponseEntity<List<MinhaConsulta>> dadosConsulta(@RequestBody MinhaConsultaDTO idConsulta) {
        try {
            List<MinhaConsulta> consultas = consultaService.dadosConsulta(idConsulta);
            return ResponseEntity.ok(consultas);
        } catch (ConsultaException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Collections.emptyList());
        }
    }

    @PutMapping("/cancelar-consulta")
    public ResponseEntity<Map<String, String>> cancelarConsulta(@RequestBody idMinhaConsultaDTO idConsulta) {
        try {
            consultaService.cancelarConsulta(idConsulta);
            return ResponseEntity.status(200).body(Map.of("Messagem", "Consulta cancelada com sucesso!"));
        } catch (ConsultaException e) {
            throw new ConsultaException("Erro ao cancelar a consulta: " + e.getMessage());
        }
    }
}
