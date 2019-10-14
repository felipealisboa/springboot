package br.com.scc4.tms.controller;

import br.com.scc4.tms.model.Plataforma;
import br.com.scc4.tms.repository.PlataformaRepository;
import br.com.scc4.tms.service.PlataformaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/plataformas")
public class PlataformaController {

    @Autowired
    private PlataformaService plataformaService;

    @Autowired
    private PlataformaRepository plataformaRepository;

    @GetMapping
    public List verTodasPlataformas() {
        return plataformaRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Plataforma> buscarPeloCodigo(@PathVariable Long id) {
        Optional<Plataforma> plataformaOptional = plataformaRepository.findById(id);

        return plataformaOptional.isPresent() ? ResponseEntity.ok(plataformaOptional.get()) : ResponseEntity.notFound().build();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Plataforma salvar(@Valid @RequestBody Plataforma plataforma) {
        return plataformaService.salvar(plataforma);
    }

    @PutMapping("/{id}")
    public Plataforma atualizar(@PathVariable Long id, @Valid @RequestBody Plataforma plataforma) {
        plataforma.setId(id);
        return plataformaService.salvar(plataforma);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity apagarDados(@PathVariable Long id) {
        plataformaService.apagar(id);
        return ResponseEntity.noContent().build();
    }
}
