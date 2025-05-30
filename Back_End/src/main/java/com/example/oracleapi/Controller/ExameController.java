package com.example.oracleapi.Controller;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;

import com.example.oracleapi.DTO.CpfDTO;
import com.example.oracleapi.DTO.RequisicaoExameDTO;
import com.example.oracleapi.DTO.ResultadoExameDTO;
import com.example.oracleapi.DTO.RetornoAgendamentoDTO;
import com.example.oracleapi.Entity.RequisicaoExame;
import com.example.oracleapi.Exception.ConsultaException;
import com.example.oracleapi.Exception.RequisicaoExameException;
import com.example.oracleapi.Exception.ResultadoExameExeception;
import com.example.oracleapi.Service.ExameService;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/exame")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ExameController {

    @Autowired
    private ExameService exameService;

    @PostMapping("/resultado-exame")
    public ResponseEntity<?> resultadoExame(@RequestBody ResultadoExameDTO resultadoExameDTO) {
        try {
            exameService.cadastrarResultadoExame(resultadoExameDTO);
            return ResponseEntity.status(200).body(Map.of("Mensagem", "Resultado do exame salvo com sucesso"));
        } catch (ResultadoExameExeception e) {
            throw new ResultadoExameExeception("Erro ao salvar o resultado do exame" + e.getMessage());
        }
    }

    @PostMapping("/prescricao/requisicao-exame")
    public ResponseEntity<?> prescricaoExame(@RequestPart("requisicaoExameDTO") RequisicaoExameDTO requisicaoExameDTO,
            @RequestPart("arquivo") MultipartFile arquivo) {
        try {
            exameService.cadastrarRequisicaoExame(requisicaoExameDTO, arquivo);
            return ResponseEntity.status(200).body(Map.of("Mensagem", "Requisicao exame salva com sucesso"));

        } catch (RequisicaoExameException e) {
            throw new RequisicaoExameException("Erro ao salvar a Requisicao do exame" + e.getMessage());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/listar-exame")
    public ResponseEntity<List<RequisicaoExame>> listarConsultas(@RequestBody CpfDTO cpfPaciente) {
        try {
            List<RequisicaoExame> exame = exameService.listarExames(cpfPaciente);
            return ResponseEntity.ok(exame);
        } catch (ConsultaException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Collections.emptyList());
        }
    }
}
