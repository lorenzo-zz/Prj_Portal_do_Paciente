package com.example.oracleapi.Controller;

import java.sql.SQLException;
import java.util.Map;
import javax.security.auth.login.LoginException;

import com.example.oracleapi.DTO.LoginDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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

    @PostMapping("/login")
    public ResponseEntity<Map<String,String>> login(@RequestBody @Valid LoginDTO loginDTO) throws LoginException{
        try{
            loginService.login(loginDTO);
            return ResponseEntity.status(200).body(Map.of("message", "Usuário logado com sucesso!"));

        }catch(CadastroException e){
            throw new LoginException("Erro ao logar usuário: " + e.getMessage());
        }catch(SQLException e){
            throw new CadastroException("Erro com o banco de dados: " + e.getMessage());
        }catch(Exception e){
            throw new CadastroException("Erro genérico: " + e.getMessage());
        }
    }
}
