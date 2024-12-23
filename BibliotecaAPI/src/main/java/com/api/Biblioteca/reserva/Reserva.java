package com.api.Biblioteca.reserva;

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

@Table(name = "Reserva")
@Entity(name = "Reservas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Reserva {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	private Long id;
	private String data_reserva;
	private String data_validade;
	@ManyToOne
	@JoinColumn(name = "id_livro")
	private Livro livro;
	@ManyToOne
	@JoinColumn(name = "id_pessoa")
	private Pessoa pessoa;
	
	
	public Reserva(DadosCadastroReserva dados, Livro livro, Pessoa pessoa) {
		this.data_reserva = dados.data_reserva();
		this.data_validade = dados.data_validade();
		this.livro = livro;
		this.pessoa = pessoa;
	}
	
	
	public void atualizaInformacoes(DadosAlteracaoReserva dados, Livro livro, Pessoa pessoa) {
		if(dados.data_reserva() !=null) {
			this.data_reserva = dados.data_reserva();
		}
		if(dados.data_validade() !=null) {
			this.data_validade = dados.data_validade();
		}
		if(livro !=null) {
			this.livro = livro;
		}
		if(pessoa !=null) {
			this.pessoa = pessoa;
		}
		
	}
	
	public void AtualizaStatusReserva(DadosStatusReserva dados) {
		if(dados.data_validade() !=null) {
			this.data_validade = dados.data_validade();
		}
	}
	
}
