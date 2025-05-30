package com.example.oracleapi.Service;


import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class ViaCepApi {

    private final RestTemplate restTemplate;

    public ViaCepApi() {
        this.restTemplate = new RestTemplate();
    }

    public Map<String, Object> buscarEnderecoPorCep(String cep) {
        String url = String.format("http://viacep.com.br/ws/%s/json/", cep);
        System.out.println("Consumindo: " + url);

        Map<String, Object> dados = restTemplate.getForObject(url, Map.class);
        if (dados == null || dados.containsKey("erro")) {
            throw new RuntimeException("CEP inválido ou não encontrado");
        }
        return dados;
    }
}
