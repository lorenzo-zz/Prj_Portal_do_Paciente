package com.example.oracleapi.Controller;

import java.sql.SQLException;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.oracleapi.DTO.RequisicaoExameDTO;
import com.example.oracleapi.DTO.ResultadoExameDTO;
import com.example.oracleapi.Exception.RequisicaoExameException;
import com.example.oracleapi.Exception.ResultadoExameExeception;
import com.example.oracleapi.Service.ExameService;

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
            return ResponseEntity.status(200).body(Map.of("Messagem", "Resultado do exame salvo com sucesso"));
        } catch (ResultadoExameExeception e) {
            throw new ResultadoExameExeception("Erro ao salvar o resultado do exame" + e.getMessage());
        }
    }

    @PostMapping("/prescricao/requisicao-exame")
    public ResponseEntity<?> prescricaoExame(@RequestBody RequisicaoExameDTO requisicaoExameDTO) {
        try {
            exameService.cadastrarRequisicaoExame(requisicaoExameDTO);
            return ResponseEntity.status(200).body(Map.of("Massagem", "Requisicao exame salva com sucesso"));

        } catch (RequisicaoExameException e) {
            throw new RequisicaoExameException("Erro ao salvar a Requisicao do exame" + e.getMessage());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
