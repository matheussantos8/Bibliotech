package com.api.Biblioteca.livro;



public record DadosListagemLivro(Long id, String titulo, String isbn, String ano_publicacao, String genero, String autor, String foto, String status) {
	public DadosListagemLivro(Livro dados) {
		this(dados.getId(), dados.getTitulo(), dados.getIsbn(), dados.getAno_publicacao(), dados.getGenero().getNome(), dados.getAutor().getNome(), dados.getFoto(), dados.getStatus());

}
}
