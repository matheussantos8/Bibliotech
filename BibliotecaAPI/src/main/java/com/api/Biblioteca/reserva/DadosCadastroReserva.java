package com.api.Biblioteca.reserva;

public record DadosCadastroReserva(String nome, String data_reserva, String data_validade, Long id_livro, Long id_pessoa) {

}
