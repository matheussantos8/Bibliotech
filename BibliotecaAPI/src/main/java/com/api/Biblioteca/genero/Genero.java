package com.api.Biblioteca.genero;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "Genero")
@Entity(name = "Generos")
@Getter 
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")


public class Genero {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	private Long id;
	private String nome;
	
	 public Genero(DadosCadastroGenero dados) {
	        this.nome = dados.nome();
	    }
	 
	 public void atualizarInformacoes(DadosAlteracaoGenero dados) {
	        if (dados.nome() != null) {
	            this.nome = dados.nome();
	        }
}
}
