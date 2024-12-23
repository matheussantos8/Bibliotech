package com.api.Biblioteca.emprestimo;

public record DadosAlteracaoEmprestimo(Long id, String data_emprestimo, String data_devolucao, Long id_livro, Long id_pessoa) {
	
}
