package com.api.Biblioteca.pessoa;


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

@Table(name = "Pessoa")
@Entity(name = "Pessoas")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")


public class Pessoa {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	private Long id;
	private String nome;
	private String email;
	private String telefone;
	
	public Pessoa(DadosCadastroPessoa dados) {
		this.nome = dados.nome();
		this.email = dados.email();
		this.telefone = dados.telefone();
	}
	
	public void atualizaInformacoes(DadosAlteracaoPessoa dados) {
		if(dados.nome() != null) {
			this.nome = dados.nome();
		}
		if(dados.email() != null) {
			this.email = dados.email();
		}
		if(dados.telefone() != null) {
			this.telefone = dados.telefone();
		}
	}

	
}
