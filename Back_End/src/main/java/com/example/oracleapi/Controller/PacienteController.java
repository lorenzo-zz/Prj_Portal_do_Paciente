package com.example.oracleapi.Controller;

import com.example.oracleapi.DTO.*;
import com.example.oracleapi.Exception.AlergiaException;
import com.example.oracleapi.Exception.DadosPacienteException;
import com.example.oracleapi.Service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.sql.SQLException;
import java.util.Map;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/paciente")
public class PacienteController {

    @Autowired
    private PacienteService pacienteService;

    @PostMapping("/alergias")
    public ResponseEntity<?> alergia(@RequestBody PacienteAlergiaDTO pacienteAlergiaDTO) {
        try {
            pacienteService.cadastrarAlergia(pacienteAlergiaDTO);
            return ResponseEntity.status(HttpStatus.OK).body(Map.of("mensagem", "Alergia cadastrada com sucesso"));
        } catch (AlergiaException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("erro", e.getMessage()));
        } catch (SQLException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("erro", "Erro ao acessar o banco de dados"));
        }
    }

    @DeleteMapping("/alergias-remover")
    public ResponseEntity<?> alergiaRemover(@RequestBody PacienteAlergiaDTO pacienteAlergiaDTO) {
        try {
            pacienteService.alergiaRemover(pacienteAlergiaDTO);
            return ResponseEntity.status(200).body(Map.of("mensagem", "Alergia removida com sucesso"));
        } catch (AlergiaException e) {
            throw new AlergiaException("Erro ao remover alergia" + e.getMessage());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/dados-paciente")
    public ResponseEntity<?> dadosPaciente(@RequestBody CpfDTO cpfDTO) throws SQLException {
        try {
            RetornoPacienteDTO paciente = pacienteService.dadosDoPaciente(cpfDTO.cpf());
            return ResponseEntity.ok(paciente);
        } catch (DadosPacienteException e) {
            throw new DadosPacienteException("Erro ao trazer os dados do paciente" + e.getMessage());

        } catch (SQLException e) {
            throw new SQLException("Erro genérico" + e.getMessage());
        }
    }

    @PutMapping("/atualizar-dados")
    public ResponseEntity<?> atualizaDados(@RequestBody AtualizarPacienteDTO atualizarPacienteDTO) throws SQLException {
        try {
            pacienteService.atualizarDadosPaciente(atualizarPacienteDTO);
            return ResponseEntity.status(200).body(Map.of("Messagem", "Sucesso ao salvar suas novas informações"));
        } catch (DadosPacienteException e) {
            throw new DadosPacienteException("Erro ao salvar os dados do paciente" + e.getMessage());

        }
    }

    @PostMapping("/listar-alergias")
    public ResponseEntity<?> listarAlergias(@RequestBody CpfDTO cpfDTO) {
        try {
            var lista = pacienteService.listarAlergiasPorCpf(cpfDTO.cpf());
            return ResponseEntity.ok(lista);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("erro", "Erro ao listar alergias: " + e.getMessage()));
        }
    }
}
