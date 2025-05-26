package com.example.oracleapi.Controller;


import com.example.oracleapi.DTO.*;
import com.example.oracleapi.Exception.*;
import com.example.oracleapi.Service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.sql.SQLException;
import java.util.Map;
import com.example.oracleapi.Exception.DadosPacienteException;

@RestController
@RequestMapping("/paciente")
public class PacienteController {

    @Autowired
    private PacienteService pacienteService;

    @PostMapping("/agendar-consulta")
    public ResponseEntity<?> consulta(@RequestBody AgendamentoConsultaDTO agendamentoConsultaDTO) {
        try {
            pacienteService.agendarConsulta(agendamentoConsultaDTO);
            return ResponseEntity.status(200).body(Map.of("mensagem", "Agendamento concluido com sucesso"));
        } catch (ConsultaException | SQLException e) {
            throw new ConsultaException("Erro ao cadastrar consulta" + e.getMessage());
        }
    }

    @PostMapping("/minha-consulta")
    public ResponseEntity<?> MinhaConsulta(@RequestBody MinhaConsultaDTO minhaConsultaDTO){
        try {
            pacienteService.minhaConsulta(minhaConsultaDTO);
            return ResponseEntity.status(200).body(Map.of("mensagem", "Aqui está sua consulta"));
        }catch (ConsultaException e){
            throw new ConsultaException("Erro ao retornar sua consulta" + e.getMessage());
        }
    }

    @PostMapping("/alergias")
    public ResponseEntity<?> alergia(@RequestBody PacienteAlergiaDTO pacienteAlergiaDTO) {
        try {
            pacienteService.cadastrarAlergia(pacienteAlergiaDTO);
            return ResponseEntity.status(200).body(Map.of("mensagem", "Alergia cadastrada com sucesso"));
        } catch (AlergiaException e) {
            throw new AlergiaException("Erro ao cadastrar alergia" + e.getMessage());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/medico")
    public ResponseEntity<?> medico(@RequestBody MedicoDTO medicoDTO) {
        try {
            pacienteService.cadastrarMedico(medicoDTO);
            return ResponseEntity.status(200).body(Map.of("Messagem", "Medico cadastrado com sucesso"));
        } catch (MedicoException e) {
            throw new MedicoException("Erro ao cadastrar o medico" + e.getMessage());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/prescricao")
    public ResponseEntity<?> prescricao(@RequestBody PrescricaoDTO prescricaoDTO) {
        try {
            pacienteService.cadastrarPrescricao(prescricaoDTO);
            return ResponseEntity.status(200).body(Map.of("Messagem", "Prescrição cadastrado com sucesso"));
        } catch (PrescricaoException e) {
            throw new PrescricaoException("Erro ao cadastrar o prescrição" + e.getMessage());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/prescricao/requisicao-exame")
    public ResponseEntity<?> prescricaoExame(@RequestBody RequisicaoExameDTO requisicaoExameDTO) {
        try{
            pacienteService.cadastrarRequisicaoExame(requisicaoExameDTO);
            return ResponseEntity.status(200).body(Map.of("Massagem", "Requisicao exame salva com sucesso"));

        }catch (RequisicaoExameException e){
            throw new RequisicaoExameException("Erro ao salvar a Requisicao do exame" + e.getMessage());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/resultado-consulta")
    public ResponseEntity<?> resultadoConsulta (@RequestBody ResultadoConsultaDTO resultadoExameDTO){
        try{
            pacienteService.cadastrarResultadoConsulta(resultadoExameDTO);
            return ResponseEntity.status(200).body(Map.of("Messagem", "Resultado da consulta salvo com sucesso"));
        }catch (ResultadoConsultaExeception | SQLException e){
            throw new ResultadoConsultaExeception("Erro ao salvar o resultado do exame" +  e.getMessage());
        }
    }

    @PostMapping("/resultado-exame")
    public ResponseEntity<?> resultadoExame(@RequestBody ResultadoExameDTO resultadoExameDTO){
        try{
            pacienteService.cadastrarResultadoExame(resultadoExameDTO);
            return ResponseEntity.status(200).body(Map.of("Messagem", "Resultado do exame salvo com sucesso"));
        }catch (ResultadoExameExeception e){
            throw new ResultadoExameExeception("Erro ao salvar o resultado do exame" +  e.getMessage());
        }
    }    @PostMapping("/dados-paciente")
    public ResponseEntity<?> dadosPaciente(@RequestBody CpfDTO cpfDTO) throws SQLException {
        try{
            return ResponseEntity.status(200).body(Map.of("Messagem",pacienteService.dadosDoPaciente(cpfDTO.cpf())));
        } catch (RuntimeException e){
            throw new DadosPacienteException("Paciente não encontrado");
        } catch (SQLException e) {
            throw new SQLException("Erro genérico" + e.getMessage());
        }
    }
//
//    @PutMapping("/edição")

}



