package com.api.Biblioteca.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.api.Biblioteca.pessoa.DadosAlteracaoPessoa;
import com.api.Biblioteca.pessoa.DadosCadastroPessoa;
import com.api.Biblioteca.pessoa.DadosListagemPessoa;
import com.api.Biblioteca.pessoa.Pessoa;

import com.api.Biblioteca.pessoa.PessoaRepository;

import jakarta.transaction.Transactional;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/pessoas")
public class PessoaController {

	@Autowired
	private PessoaRepository repository;

	
	@PostMapping
	@Transactional
	public ResponseEntity<?> cadastroPessoa(@RequestBody DadosCadastroPessoa dados) {
		var pessoa = new Pessoa(dados);
		repository.save(pessoa);
		URI location = ServletUriComponentsBuilder.fromCurrentContextPath().path("/{id}").buildAndExpand(pessoa.getId())
				.toUri();
		return ResponseEntity.created(location).body(pessoa);

	}

	
	@GetMapping
	public ResponseEntity<List<DadosListagemPessoa>> listarPessoa() {
		var lista = repository.findAll().stream().map(DadosListagemPessoa::new).toList();
		return ResponseEntity.ok(lista);
	}

	
	@PutMapping
	@Transactional
	public ResponseEntity<?> atualizaPessoa(@RequestBody DadosAlteracaoPessoa dados) {
		if (!repository.existsById(dados.id())) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pessoa não encontrada");
		}
		var pessoa = repository.getReferenceById(dados.id());
		pessoa.atualizaInformacoes(dados);

		return ResponseEntity.ok(dados);
	}

	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> excluiPessoa(@PathVariable Long id) {
		if (!repository.existsById(id)) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pessoa não encontrada");
		}
		repository.deleteById(id);
		return ResponseEntity.noContent().build();
	}

	
	@GetMapping("/{id}")
	public ResponseEntity<?> detalhar(@PathVariable Long id) {
		if (!repository.existsById(id)) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pessoa não encontrado");
		}
		var pessoa = repository.getReferenceById(id);
		DadosListagemPessoa dados = new DadosListagemPessoa(pessoa);
		return ResponseEntity.ok(dados);
	}

}
