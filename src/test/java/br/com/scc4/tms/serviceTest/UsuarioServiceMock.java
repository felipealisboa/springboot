package br.com.scc4.tms.serviceTest;

import br.com.scc4.tms.model.Empresa;
import br.com.scc4.tms.model.Usuario;
import br.com.scc4.tms.repository.EmpresaRepository;
import br.com.scc4.tms.repository.UsuarioRepository;
import br.com.scc4.tms.service.UsuarioService;
import br.com.scc4.tms.service.exception.RegistroJaExisteException;
import br.com.scc4.tms.service.exception.RegistroNaoEncontradoException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Optional;

import static org.mockito.Mockito.when;

public class UsuarioServiceMock{

    private UsuarioService usuarioService;

    @Mock
    private UsuarioRepository usuarioRepositoryMocked;

    @Mock
    private EmpresaRepository empresaRepositoryMock;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        usuarioService = new UsuarioService(usuarioRepositoryMocked);
    }

    @Test(expected = RegistroJaExisteException.class)
    public void canNotCreateUserThatExist() {
        Usuario usuarioTest = getUsuarioDaBd();

        when(usuarioRepositoryMocked.findById(1L)).thenReturn((Optional.of(usuarioTest)));
    }

    @Test(expected = RegistroNaoEncontradoException.class)
    public void canNotDeleteUserThatExist() {
        Usuario usuarioTest = getUsuarioDaBd();

        when(usuarioRepositoryMocked.findById(1L)).thenReturn((Optional.of(usuarioTest)));
    }


    private Usuario getUsuarioDaBd() {
        Usuario usuario = new Usuario();
        usuario.setId(1L);
        usuario.setCpf("112.118.130-90");
        usuario.setNome("Rui Barros");
        usuario.setEmail("rui@gmail.com");
        usuario.setEmpresa(new Empresa());
        usuario.setSenha("123teste123testando");
        usuario.setStatus(true);
        usuario.setDataExpiracao(LocalDate.parse("18/10/2020"));

        return usuario;
    }

}
