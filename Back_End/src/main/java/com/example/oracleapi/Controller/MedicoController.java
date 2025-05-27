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

import com.example.oracleapi.DTO.MedicoDTO;
import com.example.oracleapi.Exception.MedicoException;
import com.example.oracleapi.Service.MedicoService;

@RestController
@RequestMapping("/medico")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class MedicoController {

    @Autowired
    private MedicoService medicoService;

    @PostMapping("/medico")
    public ResponseEntity<?> medico(@RequestBody MedicoDTO medicoDTO) {
        try {
            medicoService.cadastrarMedico(medicoDTO);
            return ResponseEntity.status(200).body(Map.of("Messagem", "Medico cadastrado com sucesso"));
        } catch (MedicoException e) {
            throw new MedicoException("Erro ao cadastrar o medico" + e.getMessage());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
