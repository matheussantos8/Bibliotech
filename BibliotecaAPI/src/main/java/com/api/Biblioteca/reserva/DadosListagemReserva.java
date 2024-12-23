package com.api.Biblioteca.reserva;

public record DadosListagemReserva(Long id, String data_reserva, String data_validade, String livro, String pessoa) {
	public DadosListagemReserva(Reserva dados) {
		this(dados.getId(), dados.getData_reserva(), dados.getData_validade(), dados.getLivro().getTitulo(), dados.getPessoa().getNome());
	}
}
