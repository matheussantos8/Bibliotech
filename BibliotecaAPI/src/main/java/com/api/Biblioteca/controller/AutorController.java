package com.api.Biblioteca.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.api.Biblioteca.autor.Autor;
import com.api.Biblioteca.autor.AutorRepository;
import com.api.Biblioteca.autor.DadosAlteracaoAutor;
import com.api.Biblioteca.autor.DadosCadastroAutor;
import com.api.Biblioteca.autor.DadosListagemAutor;

import jakarta.transaction.Transactional;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/autores")
public class AutorController {

	@Autowired
	private AutorRepository repository;

	
	@PostMapping
	@Transactional
	public ResponseEntity<?> cadastroAutor(@RequestBody DadosCadastroAutor dados) {
		var autor = new Autor(dados);
		repository.save(autor);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(autor.getId())
				.toUri();
		return ResponseEntity.created(location).body(autor);

	}

	
	@GetMapping
	public ResponseEntity<List<DadosListagemAutor>> listarAutor() {
		var lista = repository.findAll().stream().map(DadosListagemAutor::new).toList();
		return ResponseEntity.ok(lista);
	}

	
	@PutMapping
	@Transactional
	public ResponseEntity<?> AtualizaAutor(@RequestBody DadosAlteracaoAutor dados) {
		if (!repository.existsById(dados.id())) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Autor não encontrado");
		}
		var autor = repository.getReferenceById(dados.id());
		autor.AtualizaInformacoes(dados);
		return ResponseEntity.ok(dados);

	}

	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> excluirAutor(@PathVariable Long id) {
		if (!repository.existsById(id)) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Autor não encontrado");
		}
		repository.deleteById(id);
		return ResponseEntity.noContent().build();
	}

	
	@GetMapping("/{id}")
	public ResponseEntity<?> detalhar(@PathVariable Long id) {
		if (!repository.existsById(id)) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Autor não encontrado");
		}
		var autor = repository.getReferenceById(id);
		DadosListagemAutor dados = new DadosListagemAutor(autor);
		return ResponseEntity.ok(dados);
	}

}
