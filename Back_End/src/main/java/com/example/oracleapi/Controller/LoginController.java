package com.example.oracleapi.Controller;

import com.example.oracleapi.DTO.LoginDTO;
import com.example.oracleapi.Entity.Paciente;
import com.example.oracleapi.Exception.CadastroException;
import com.example.oracleapi.Service.LoginService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.security.auth.login.LoginException;
import java.sql.SQLException;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/autenticar")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping("/cadastrar")
    public ResponseEntity<Map<String, String>> cadastrar(@RequestPart("paciente") @Valid Paciente paciente,
                                                         @RequestParam("arquivo")  MultipartFile arquivo) {

        try {
            loginService.cadastrar(paciente);
            loginService.salvarDocumento(paciente.getCpf(), arquivo);
            return ResponseEntity.status(200).body(Map.of("message", "Usuário cadastrado com sucesso!"));
        } catch (CadastroException e) {
            throw new CadastroException("Erro ao cadastrar usuário: " + e.getMessage());
        } catch (SQLException e) {
            throw new CadastroException("Erro com o banco de dados: " + e.getMessage());
        } catch (Exception e) {
            throw new CadastroException("Erro genérico: " + e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody @Valid LoginDTO loginDTO) throws LoginException {
        try {
            loginService.login(loginDTO);
            return ResponseEntity.status(200).body(Map.of("message", "Usuário logado com sucesso!"));

        } catch (CadastroException e) {
            throw new LoginException("Erro ao logar usuário: " + e.getMessage());
        } catch (SQLException e) {
            throw new CadastroException("Erro com o banco de dados: " + e.getMessage());
        } catch (Exception e) {
            throw new CadastroException("Erro genérico: " + e.getMessage());
        }
    }
}
