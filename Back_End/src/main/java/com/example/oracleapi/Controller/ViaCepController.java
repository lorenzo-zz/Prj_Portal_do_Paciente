package com.example.oracleapi.Controller;


import com.example.oracleapi.Service.ViaCepApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/cep")
public class ViaCepController {


    private final ViaCepApi viaCepApi;

    @Autowired
    public ViaCepController(ViaCepApi viaCepApi) {
        this.viaCepApi = viaCepApi;
    }

    @GetMapping("/{cep}")
    public ResponseEntity<?> buscarEndereco(@PathVariable String cep) {
        try {
            Map<String, Object> endereco = viaCepApi.buscarEnderecoPorCep(cep);
            return ResponseEntity.ok(endereco);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("erro", e.getMessage()));
        }
    }

}
