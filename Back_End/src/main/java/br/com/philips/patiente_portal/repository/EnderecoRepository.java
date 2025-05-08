package br.com.philips.patiente_portal.repository;

import br.com.philips.patiente_portal.Entity.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnderecoRepository extends JpaRepository<Endereco, Long> {
}
