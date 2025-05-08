package br.com.philips.patiente_portal.repository;

import br.com.philips.patiente_portal.Entity.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {
}
