package br.com.scc4.tms.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="plataforma")
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Plataforma {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @NotBlank(message = "plataformas-1")
    @Column(name = "nome_plataforma")
    private String nomePlataforma;

    @NotBlank(message = "plataformas-2")
    @Column(name = "sigla_plataforma")
    private String siglaPlataforma;

    @NotBlank(message = "plataformas-3")
    private String url;

    @NotNull(message = "plataformas-4")
    private Boolean status;

    @JsonIgnore
    public boolean isNew() {
        return id == null;
    }

}
