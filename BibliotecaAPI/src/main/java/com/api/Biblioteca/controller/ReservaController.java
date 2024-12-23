package com.api.Biblioteca.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.api.Biblioteca.livro.Livro;
import com.api.Biblioteca.livro.LivroRepository;
import com.api.Biblioteca.reserva.DadosAlteracaoReserva;
import com.api.Biblioteca.reserva.DadosCadastroReserva;
import com.api.Biblioteca.reserva.DadosListagemReserva;
import com.api.Biblioteca.reserva.DadosStatusReserva;
import com.api.Biblioteca.reserva.Reserva;
import com.api.Biblioteca.reserva.ReservaRepository;

import jakarta.transaction.Transactional;

import com.api.Biblioteca.pessoa.Pessoa;
import com.api.Biblioteca.pessoa.PessoaRepository;

@RestController
@RequestMapping("/reservas")
public class ReservaController {

	@Autowired
	private ReservaRepository ReservaRepository;

	@Autowired
	private LivroRepository LivroRepository;

	@Autowired
	private PessoaRepository PessoaRepository;

	
	@PostMapping
	@Transactional
	public ResponseEntity<?> PublicarReserva(@RequestBody DadosCadastroReserva dados) {
		Livro livro = LivroRepository.findById(dados.id_livro()).orElse(null);
		if(livro == null) {
			return ResponseEntity.notFound().build();
		}
		Pessoa pessoa = PessoaRepository.findById(dados.id_pessoa()).orElse(null);
		if(pessoa == null) {
			return ResponseEntity.notFound().build();
		}
		Reserva reserva = ReservaRepository.save(new Reserva(dados, livro, pessoa));
		Long id = reserva.getId();
		URI location = ServletUriComponentsBuilder.fromCurrentContextPath().path("/{id}")
				.buildAndExpand(id).toUri();
		return ResponseEntity.created(location).build();

	}

	
	@GetMapping
	public ResponseEntity<List<DadosListagemReserva>> listar() {
		var lista = ReservaRepository.findAll().stream().map(DadosListagemReserva::new).toList();
		return ResponseEntity.ok(lista);

	}

	
	@PutMapping
	@Transactional
	public ResponseEntity<?> AtualizaReserva(@RequestBody DadosAlteracaoReserva dados) {
		if (!ReservaRepository.existsById(dados.id())) {
			return ResponseEntity.badRequest().body("Reserva não encontrado");
		}
		Livro livro = LivroRepository.findById(dados.id_livro()).orElse(null);
		if(livro == null) {
			return ResponseEntity.notFound().build();
		}
		Pessoa pessoa = PessoaRepository.findById(dados.id_pessoa()).orElse(null);
		if(pessoa == null) {
			return ResponseEntity.notFound().build();
		}
		var reserva = ReservaRepository.getReferenceById(dados.id());
		reserva.atualizaInformacoes(dados, livro, pessoa);
		return ResponseEntity.ok(reserva);

	}

	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<?> atualizaStatus(@PathVariable Long id, @RequestBody DadosStatusReserva dados) {
		if (!ReservaRepository.existsById(id)) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Reserva não encontrada");
		}
		var status = ReservaRepository.getReferenceById(id);
		status.AtualizaStatusReserva(dados);
		return ResponseEntity.ok(status);
	}

	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> excluirReserva(@PathVariable Long id) {
		if (!ReservaRepository.existsById(id)) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Reserva não encontrada");
		}
		ReservaRepository.deleteById(id);
		return ResponseEntity.noContent().build();

	}

	
	@GetMapping("/{id}")
	public ResponseEntity<?> detalhar(@PathVariable Long id) {
		if (!ReservaRepository.existsById(id)) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Reserva não encontrado");
		}
		var reserva = ReservaRepository.getReferenceById(id);
		DadosListagemReserva dados = new DadosListagemReserva(reserva);
		return ResponseEntity.ok(dados);
	}

}
