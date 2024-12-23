package com.api.Biblioteca.emprestimo;

public record DadosListagemEmprestimo(Long id, String data_emprestimo, String data_devolucao, String Livro, String Pessoa) {
	public DadosListagemEmprestimo(Empréstimo dados) {
		this(dados.getId(), dados.getData_emprestimo(), dados.getData_devolucao(), dados.getLivro().getTitulo(), dados.getPessoa().getNome());
	}
}
