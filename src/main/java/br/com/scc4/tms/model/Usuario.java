package br.com.scc4.tms.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="usuario")
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;


    @NotBlank(message = "usuarios-1")
    @Size(min=2, max=200, message = "max-length-200")
    private String nome;

    @JsonIgnore
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String senha;

    @NotBlank(message = "usuarios-3")
    @Size(min=2, max=200, message = "max-length-200")
    @Email(message = "max-length-200")
    private String email;

    @NotNull(message = "usuarios-4")
    private Boolean status;

    @CPF(message = "usuarios-5")
    @NotBlank(message = "usuarios-6")
    @Size(min=2, max=18, message = "max-length-18")
    private String cpf;

    @Column(name = "data_expiracao")
    @JsonFormat(pattern ="dd/MM/yyyy")
    private LocalDate dataExpiracao;

    @NotNull
    @ManyToOne
    @JoinColumn(name="id_empresa")
    private Empresa empresa;

    @ManyToMany( cascade = CascadeType.ALL, fetch = FetchType.EAGER )
    @JoinTable(
            name = "usuarios_roles",
            joinColumns = {@JoinColumn(name="usuario_id")},
            inverseJoinColumns = {@JoinColumn(name="role_id")}
    )
    private Set<Role> roles = new HashSet<>();


    @PrePersist @PreUpdate
    private void prePersistPreUpdate() {
        if (this.cpf != null) {
            this.cpf = removerFormatacao(this.cpf);
        }
    }

    @JsonIgnore
    public boolean isNew() {
        return getId() == null;
    }

    public String removerFormatacao(String campo) {
        return campo.replaceAll("[^0-9]", "");
    }
}
