package br.com.scc4.tms.repository;

import br.com.scc4.tms.model.Plataforma;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PlataformaRepository extends JpaRepository<Plataforma, Long> {

    Optional<Plataforma> findByNomePlataforma(String nomePlataforma);
}
