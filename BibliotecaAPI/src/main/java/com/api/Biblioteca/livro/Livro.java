package com.api.Biblioteca.livro;


import com.api.Biblioteca.autor.Autor;
import com.api.Biblioteca.genero.Genero;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Table(name = "Livro")
@Entity(name = "Livros")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")

public class Livro {
	
	public Livro(DadosCadastroLivro dados, Genero genero, Autor autor) {
		this.titulo = dados.titulo();
		this.isbn = dados.isbn();
		this.ano_publicacao = dados.ano_publicacao();
		this.genero = genero;
		this.autor = autor;
		this.foto = dados.foto();
		this.status = "DISPONIVEL";

	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	
	

	
	private Long id;
	private String titulo;
	private String isbn;
	private String ano_publicacao;
	@ManyToOne
	@JoinColumn(name = "id_genero")
	private Genero genero;
	@ManyToOne
	@JoinColumn(name = "id_autor")
	private Autor autor;
	@Lob
	private String foto;
	private String status;
	
	
	public void atualizaInformacoes(DadosAlteracaoLivro dados, Genero genero, Autor autor) {
		if (dados.titulo() != null) {
			this.titulo = dados.titulo();
		}
		if (dados.isbn() != null) {
			this.isbn = dados.isbn();
		}
		if (dados.ano_publicacao() != null) {
			this.ano_publicacao = dados.ano_publicacao();
		}
		if (genero != null) {
			this.genero = genero;
		}
		if (autor != null) {
			this.autor = autor;
		}
		if(dados.foto() != null) {
			this.foto = dados.foto();
		}
	}
	
	public void atualizaStatus(String status) {
		this.status = status;

	}
}
