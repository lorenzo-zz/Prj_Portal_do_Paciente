package com.example.oracleapi.Service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.Map;

@Service
public class ViaCepApi {

    @Autowired
    RestTemplate template;

    public Map<String, Object> buscarEnderecoPorCep(String cep) {
        String url = String.format("https://viacep.com.br/ws/%s/json/", cep);

        Map<String, Object> dados = template.getForObject(url, Map.class);
        if (dados == null || dados.containsKey("erro")) {
            throw new RuntimeException("CEP inválido ou não encontrado");
        }
        return dados;
    }
}
