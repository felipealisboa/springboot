package br.com.scc4.tms.repository;

import br.com.scc4.tms.model.Empresa;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmpresaRepository extends JpaRepository<Empresa, Long> {
    Optional<Empresa> findByCnpj(String cnpj);

    Optional<Empresa> findFirst1ByPlataformaId(Long idPlataforma);
}
