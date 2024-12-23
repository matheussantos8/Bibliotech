package com.api.Biblioteca.emprestimo;

import com.api.Biblioteca.livro.Livro;
import com.api.Biblioteca.pessoa.Pessoa;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "Emprestimo")
@Entity(name = "Emprestimos")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")

public class Empréstimo {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	private Long id;
	private String data_emprestimo;
	private String data_devolucao;
	@ManyToOne
	@JoinColumn(name = "id_livro")
	private Livro livro;
	@ManyToOne
	@JoinColumn(name = "id_pessoa")
	private Pessoa pessoa;
	
	

	public Empréstimo(DadosCadastroEmprestimo dados, Livro livro, Pessoa pessoa) {
		this.data_emprestimo = dados.data_emprestimo();
		this.data_devolucao = dados.data_devolucao();
		this.livro = livro;
		this.pessoa = pessoa;
	}
	
	public void atualizaInformacoes(DadosAlteracaoEmprestimo dados, Livro livro, Pessoa pessoa) {
		if(dados.data_emprestimo() !=null) {
			this.data_emprestimo = dados.data_emprestimo();
		}
		if(dados.data_devolucao() !=null) {
			this.data_devolucao = dados.data_devolucao();
		}
		if(livro != null) {
			this.livro = livro;
		}
		if(pessoa != null) {
			this.pessoa = pessoa;
		}
		
	}

	
	public void atualizaDataDevolucao(String data) {
		if (data != null) {
			this.data_devolucao = data;
		}

	
}}

	

	

