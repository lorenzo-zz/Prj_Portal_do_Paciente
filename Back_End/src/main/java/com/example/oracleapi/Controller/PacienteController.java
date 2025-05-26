package com.example.oracleapi.Controller;

import com.example.oracleapi.Service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.Map;

@CrossOrigin(origins = "*")
@RequestMapping("/paciente")
@RestController
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
    public ResponseEntity<?> MinhaConsulta(@RequestBody MinhaConsultaDTO minhaConsultaDTO) {
        try { 
            pacienteService.minhaConsulta(minhaConsultaDTO); 
            return ResponseEntity.status(200).body(Map.of("mensagem", "Aqui está sua consulta"));
        } catch (ConsultaException e) {
             throw new ConsultaExcept ion("Erro ao retornar sua consulta" + e.getMessage());
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

    @PostMapping("/alergias-remover")
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
        try  {
             pacienteService.cadastrarPrescricao(prescricaoDTO);
             return ResponseEntity.status(200).body(Map.of("Messagem", "Prescrição cadastrado com sucesso"));
        } catch (PrescricaoException e) {
             throw new PrescricaoException(" Erro ao cadastrar o prescrição" + e.getMessage());
        }  catch (SQLException e) { 
             throw new RuntimeException(e); 
        }
    }

    @PostMapping("/prescricao/requisicao-exame")
    public ResponseEntity<?> prescricaoExame(@RequestBody RequisicaoExameDTO requisicaoExameDTO) {
        try{ 
             pacienteService.cadastrarRequisicoExame(requisicaoExameDTO); 
             return ResponseEntity.status(200)body(Map.of("Massagem", "Requisicao exame salva com s ucesso"));
 
        } catch (RequisicaoExameException e){ 
             throw new RequisicaoExameException("Erro ao salva r a Requisicao do exame" + e etMessage());
        }  catch (SQLException e) {  
            throw new RuntimeException(e); 
        }
    }
 
    @PostMa pping("/resultado-consulta") 
    public  ResponseEntity<?> resultadoConsulta (@RequestBody ResultadoConsultaDTO resultadoE xameDTO){
        try {
             pacienteService.cadastrarResult adoConsulta(resultadoExameDTO);
             return ResponseEntity.status(20 0).body(Map.of("Messagem", "Resultado da co ulta salvo com sucesso"));
        } catch (ResultadoConsultaExeception  | SQLException e){ 
      



                @Post
                 
             
                        pacienteService.c
                        return ResponseEnt
                    }catch (ResultadoE
                 
             
         
    @
 
        RetornoPacienteDTO paciente = pacienteService.dadosDoPaciente(cpf);
        return ResponseEntity.ok(paciente);
    } catch (SQLException e) {
        return ResponseEntity.status(500).body(Map.of("erro", "Erro ao buscar paciente: " + e.getMessage()));
    }
}
}

