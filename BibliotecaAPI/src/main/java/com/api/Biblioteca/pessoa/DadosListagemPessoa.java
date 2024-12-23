package com.api.Biblioteca.pessoa;

public record DadosListagemPessoa(Long id, String nome, String email, String telefone) {
		public DadosListagemPessoa(Pessoa dados) {
			this(dados.getId(), dados.getNome(), dados.getEmail(), dados.getTelefone());
		}
}
