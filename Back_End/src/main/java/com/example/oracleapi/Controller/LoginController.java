package com.example.oracleapi.Controller;

import com.example.oracleapi.DTO.EnderecoDTO;
import com.example.oracleapi.DTO.LoginDTO;
import com.example.oracleapi.Entity.Paciente;
import com.example.oracleapi.Exception.CadastroException;
import com.example.oracleapi.Service.LoginService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.sql.SQLException;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/autenticar")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping("/cadastrar")
    public ResponseEntity<?> cadastrar(
            @RequestPart("paciente") Paciente paciente,
            @RequestPart("arquivo") MultipartFile arquivo) {
        try {
            loginService.cadastrar(paciente, arquivo);
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
    public ResponseEntity<Map<String, String>> login(@RequestBody @Valid LoginDTO loginDTO) {
        try {
            Paciente paciente = loginService.login(loginDTO); // retornando paciente
            return ResponseEntity.status(200).body(Map.of(
                    "message", "Usuário logado com sucesso!",
                    "nome", paciente.getNome()));
        } catch (SQLException e) {
            return ResponseEntity.status(500).body(Map.of("erro", "Erro no banco de dados: " + e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(Map.of("erro", "Erro inesperado: " + e.getMessage()));
        }
    }

    @PostMapping("/cadastrar/endereco")
    public ResponseEntity<?> cadastroEndereco(@RequestBody EnderecoDTO endereco) {
        try {
            loginService.cadastrarEndereco(endereco);
            return ResponseEntity.status(200).body(Map.of("message", "Endereço do usuario cadastrado com sucesso"));
        } catch (SQLException e) {
            if (e.getMessage().contains("CPF já cadastrado")) {
                throw new CadastroException("CPF já cadastrado!");
            } else if (e.getMessage().contains("Email já cadastrado")) {
                throw new CadastroException("Email já cadastrado!");
            } else if (e.getMessage().contains("Idade minima de 18 anos.")) {
                throw new CadastroException("Idade mínima para cadastro é 18 anos.");
            }
            throw new CadastroException("Erro com o banco de dados: " + e.getMessage());
        }
    }

}
