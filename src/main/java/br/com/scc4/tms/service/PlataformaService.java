package br.com.scc4.tms.service;

import br.com.scc4.tms.model.Empresa;
import br.com.scc4.tms.model.Plataforma;
import br.com.scc4.tms.repository.EmpresaRepository;
import br.com.scc4.tms.repository.PlataformaRepository;
import br.com.scc4.tms.service.exception.RegistroNaoEncontradoException;
import br.com.scc4.tms.service.exception.RegistroNaoEstaVazioException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.Optional;

@Service
public class PlataformaService {

    private PlataformaRepository plataformaRepository;
    private EmpresaRepository empresaRepository;

    public PlataformaService(@Autowired PlataformaRepository plataformaRepository, @Autowired EmpresaRepository empresaRepository) {
        this.plataformaRepository = plataformaRepository;
        this.empresaRepository = empresaRepository;
    }

    @Transactional
    public Plataforma salvar(Plataforma plataforma) {
        Optional<Plataforma> plataformaOptional = plataformaRepository.findByNomePlataforma(plataforma.getNomePlataforma());
        if ((plataformaOptional.isPresent() && plataforma.isNew()) ||
            plataformaOptional.isPresent() && !plataforma.getNomePlataforma().equalsIgnoreCase(plataformaOptional.get().getNomePlataforma())) {

                plataformaRepository.saveAndFlush(plataforma);
        }
        return plataforma;
    }

    @Transactional
    public void apagar(Long id) {
        verificarSePlataformaExiste(id);
        verificarSeTemEmpresaCadastrada(id);
        plataformaRepository.deleteById(id);
    }



    public void verificarSePlataformaExiste(Long id) {
        Optional<Plataforma> optionalPlataforma = plataformaRepository.findById(id);
        if(!optionalPlataforma.isPresent()) {
            throw new RegistroNaoEncontradoException("generic-2");
        }
    }

    private void verificarSeTemEmpresaCadastrada(Long id) {
        Optional<Empresa> optionalEmpresa = empresaRepository.findFirst1ByPlataformaId(id);
        if (optionalEmpresa.isPresent()) {
            throw new RegistroNaoEstaVazioException("generic-5");
        }
    }
}
