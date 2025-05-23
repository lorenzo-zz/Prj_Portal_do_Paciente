package com.example.oracleapi.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.oracleapi.Entity.Endereco;
import java.util.Optional;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Integer> {

    Optional<Endereco> findByCepAndNumeroAndLogradouro(String cep, String numero, String logradouro);


}
