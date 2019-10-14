package br.com.scc4.tms.repository;

import br.com.scc4.tms.model.Usuario;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByCpf(String cpf);
    Optional<Usuario> findFirst1ByEmpresaId(Long id);
    Usuario findByEmail(String email);

}