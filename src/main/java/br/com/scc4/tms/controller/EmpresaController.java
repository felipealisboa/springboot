package br.com.scc4.tms.controller;

import br.com.scc4.tms.model.Empresa;
import br.com.scc4.tms.repository.EmpresaRepository;
import br.com.scc4.tms.service.EmpresaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/{tenantid}/empresas")
public class EmpresaController {

    @Autowired
    private EmpresaService empresaService;

    @Autowired
    private EmpresaRepository empresaRepository;


    @GetMapping
    public List verTodasEmpresas() {
        return empresaRepository.findAll();
    }


    @GetMapping("/{id}")
    public ResponseEntity<Empresa> buscarPeloCodigo(@PathVariable Long id) {
        Optional<Empresa> empresaOptional = empresaRepository.findById(id);

        return empresaOptional.isPresent() ? ResponseEntity.ok(empresaOptional.get()) : ResponseEntity.notFound().build();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Empresa salvar(@Valid @RequestBody Empresa empresa) {
        return empresaService.salvar(empresa);
    }

    @PutMapping("/{id}")
    public Empresa atualizar(@PathVariable Long id, @Valid @RequestBody Empresa empresa) {
        empresa.setId(id);
        return empresaService.salvar(empresa);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity apagarDados(@PathVariable Long id) {
        empresaService.apagar(id);
        return ResponseEntity.noContent().build();
    }
}
