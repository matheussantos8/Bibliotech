package com.api.Biblioteca.reserva;

public record DadosAlteracaoReserva(Long id, String nome, String data_reserva, String data_validade, Long id_livro, Long id_pessoa) {

}
