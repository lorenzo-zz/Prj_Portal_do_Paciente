package com.example.oracleapi.Controller;

import java.sql.SQLException;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.oracleapi.Entity.Paciente;
import com.example.oracleapi.Exception.CadastroException;
import com.example.oracleapi.Service.LoginService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/autenticar")
public class LoginController {
    
    @Autowired
    private LoginService loginService;

    @PostMapping("/cadastrar")
    public ResponseEntity<Map<String,String>> cadastrar(@RequestBody @Valid Paciente paciente){

        try{
            loginService.cadastrar(paciente);
            return ResponseEntity.status(200).body(Map.of("message", "Usuário cadastrado com sucesso!"));
        }catch(CadastroException e){
            throw new CadastroException("Erro ao cadastrar usuário: " + e.getMessage());
        }catch(SQLException e){
            throw new CadastroException("Erro com o banco de dados: " + e.getMessage());
        }catch(Exception e){
            throw new CadastroException("Erro genérico: " + e.getMessage());
        }
    }
}
