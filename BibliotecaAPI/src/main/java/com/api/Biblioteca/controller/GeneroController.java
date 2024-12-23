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

import com.api.Biblioteca.genero.DadosAlteracaoGenero;
import com.api.Biblioteca.genero.DadosCadastroGenero;
import com.api.Biblioteca.genero.DadosListagemGenero;
import com.api.Biblioteca.genero.Genero;
import com.api.Biblioteca.genero.GeneroRepository;

import jakarta.transaction.Transactional;

@RestController
@RequestMapping("/generos")

public class GeneroController {

	@Autowired
	private GeneroRepository repository;

	@PostMapping
	@Transactional
	public ResponseEntity<?> cadastroGenero(@RequestBody DadosCadastroGenero dados) {
		var genero = new Genero(dados);
		repository.save(genero);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(genero.getId())
				.toUri();
		return ResponseEntity.created(location).body(genero);
	}

	
	@GetMapping
	public ResponseEntity<List<DadosListagemGenero>> listarGenero() {
		var lista = repository.findAll().stream().map(DadosListagemGenero::new).toList();
		return ResponseEntity.ok(lista);
	}
	

	@PutMapping
	@Transactional
	public ResponseEntity<?> atualizarGenero(@RequestBody DadosAlteracaoGenero dados) {
		if (repository.existsById(dados.id())) {
			var genero = repository.getReferenceById(dados.id());
			genero.atualizarInformacoes(dados);
			return ResponseEntity.ok().build();
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Gênero não encontrado");
		}
	}

	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> deletar(@PathVariable Long id) {
		if (repository.existsById(id)) {
			repository.deleteById(id);
			return ResponseEntity.noContent().build();
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Gênero não encontrado");
		}

	}

	
	@GetMapping("/{id}")
	public ResponseEntity<?> detalhar(@PathVariable Long id) {
		if (!repository.existsById(id)) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Gênero não encontrado");
		}
		var genero = repository.getReferenceById(id);
		DadosListagemGenero dados = new DadosListagemGenero(genero);
		return ResponseEntity.ok(dados);
	}
}