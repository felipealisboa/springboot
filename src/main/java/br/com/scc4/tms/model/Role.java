package br.com.scc4.tms.model;

import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Table( name = "cargo" )
public class Role {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String role;
	
	@ManyToMany( mappedBy = "roles")
	private Set<Usuario> usuarios = new HashSet<>();
	
	private Role(){} // 


	
}
