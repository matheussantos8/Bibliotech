package com.api.Biblioteca.autor;

public record DadosListagemAutor(Long id, String nome) {
	public DadosListagemAutor(Autor dados) {
		this(dados.getId(), dados.getNome());
	}
}
