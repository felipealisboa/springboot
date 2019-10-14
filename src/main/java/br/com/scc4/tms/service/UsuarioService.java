package br.com.scc4.tms.service;


import br.com.scc4.tms.model.Usuario;
import br.com.scc4.tms.repository.UsuarioRepository;

import br.com.scc4.tms.service.exception.RegistroJaExisteException;
import br.com.scc4.tms.service.exception.RegistroNaoEncontradoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UsuarioService implements UserDetailsService {

    private UsuarioRepository usuarioRepository;


    public UsuarioService(@Autowired UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Transactional
    public Usuario salvar(Usuario usuario) {
        validarCpf(usuario);
        usuarioRepository.saveAndFlush(usuario);
        return usuario;
    }

    @Transactional
    public void apagar(Long id) {
        verificarSeUsuarioExiste(id);
        usuarioRepository.deleteById(id);
    }

    private void validarCpf(Usuario usuario) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findByCpf(usuario.getCpf());
        if (usuarioOptional.isPresent() && usuario.isNew() ||
                usuarioOptional.isPresent() && !usuario.getCpf().equalsIgnoreCase(usuarioOptional.get().getCpf())) {
            throw new RegistroJaExisteException("generic-3");
        }
    }

    public boolean verificarSeUsuarioExiste(Long id) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(id);
        if(!usuarioOptional.isPresent()) {
            throw new RegistroNaoEncontradoException("generic-2");
        }
        return true;
    }


    public Usuario findByEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = findByEmail(username);
        if( usuario == null ){
            throw new UsernameNotFoundException(username);
        }

        return new UserDetailsImpl(usuario);
    }
}
