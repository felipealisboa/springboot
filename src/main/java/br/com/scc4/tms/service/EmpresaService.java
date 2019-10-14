package br.com.scc4.tms.service;

import br.com.scc4.tms.model.Empresa;
import br.com.scc4.tms.model.Plataforma;
import br.com.scc4.tms.model.Usuario;
import br.com.scc4.tms.repository.EmpresaRepository;

import br.com.scc4.tms.repository.PlataformaRepository;
import br.com.scc4.tms.repository.UsuarioRepository;
import br.com.scc4.tms.service.exception.RegistroJaExisteException;
import br.com.scc4.tms.service.exception.RegistroNaoEncontradoException;
import br.com.scc4.tms.service.exception.RegistroNaoEstaVazioException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmpresaService {


    private UsuarioRepository usuarioRepository;
    private EmpresaRepository empresaRepository;
    private PlataformaRepository plataformaRepository;

    public EmpresaService(
            @Autowired EmpresaRepository empresaRepository,
            @Autowired PlataformaRepository plataformaRepository,
            @Autowired UsuarioRepository usuarioRepository
            ) {

        this.empresaRepository = empresaRepository;
        this.plataformaRepository = plataformaRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @Transactional
    public Empresa salvar(Empresa empresa) {
        validarCnpj(empresa);
        validarPlatafor(empresa.getPlataforma());
        empresaRepository.saveAndFlush(empresa);

        return empresa;
    }

    @Transactional
    public void apagar(Long idEmpresa) {
        verificarSeEmpresaTemUsuario(idEmpresa);
        verificarSeEmpresaExiste(idEmpresa);
        empresaRepository.deleteById(idEmpresa);
    }

    private void verificarSeEmpresaExiste(Long id) {
        Optional<Empresa> optionalEmpresa = empresaRepository.findById(id);
        if(!optionalEmpresa.isPresent()) {
            throw new RegistroNaoEncontradoException("generic-2");
        }
    }

    private void verificarSeEmpresaTemUsuario(Long idEmpresa) {
        Optional<Usuario> optionalUsuario = usuarioRepository.findFirst1ByEmpresaId(idEmpresa);
        if (optionalUsuario.isPresent()) {
            throw new RegistroJaExisteException("generic-5");
        }
    }

    private void validarPlatafor(Plataforma plataforma) {
        Optional<Plataforma> plataformaOptional = plataformaRepository.findById(plataforma.getId());
        if(!plataformaOptional.isPresent()) {
            throw new RegistroNaoEncontradoException("plataformas-99");
        }
    }

    private void validarCnpj(Empresa empresa) {
        Optional<Empresa> empresaOptional = empresaRepository.findByCnpj(empresa.getCnpj());
        if (empresaOptional.isPresent() && empresa.isNew() ||
                empresaOptional.isPresent() && !empresa.getCnpj().equalsIgnoreCase(empresaOptional.get().getCnpj())) {
            throw new RegistroJaExisteException("generic-3");
        }
    }
}
