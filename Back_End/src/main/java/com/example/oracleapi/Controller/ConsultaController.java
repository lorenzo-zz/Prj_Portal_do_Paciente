package com.example.oracleapi.Controller;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.oracleapi.DTO.AgendamentoConsultaDTO;
import com.example.oracleapi.DTO.CpfDTO;
import com.example.oracleapi.DTO.MinhaConsultaDTO;
import com.example.oracleapi.DTO.PrescricaoDTO;
import com.example.oracleapi.DTO.ResultadoConsultaDTO;
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
    public ResponseEntity<?> consulta(@RequestBody AgendamentoConsultaDTO agendamentoConsultaDTO) {
        try {
            consultaService.agendarConsulta(agendamentoConsultaDTO);
            return ResponseEntity.status(200).body(Map.of("mensagem", "Agendamento concluido com sucesso"));
        } catch (ConsultaException | SQLException e) {
            throw new ConsultaException("Erro ao cadastrar consulta" + e.getMessage());
        }
    }

    @PostMapping("/minha-consulta")
    public ResponseEntity<?> MinhaConsulta(@RequestBody MinhaConsultaDTO minhaConsultaDTO) {
        try {
            consultaService.minhaConsulta(minhaConsultaDTO);
            return ResponseEntity.status(200).body(Map.of("mensagem", "Aqui está sua consulta"));
        } catch (ConsultaException e) {
            throw new ConsultaException("Erro ao retornar sua consulta" + e.getMessage());
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
    public ResponseEntity<List> listarConsultas(CpfDTO cpfPaciente) {
        try {
            return ResponseEntity.status(200).body(consultaService.listarConsultas(cpfPaciente));
        } catch (ConsultaException e) {
            throw new RuntimeException("Erro ao listar consultas: " + e.getMessage());
        }
    }
}
