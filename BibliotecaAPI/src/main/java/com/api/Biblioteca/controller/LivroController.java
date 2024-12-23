package com.api.Biblioteca.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.api.Biblioteca.autor.Autor;
import com.api.Biblioteca.autor.AutorRepository;
import com.api.Biblioteca.genero.Genero;
import com.api.Biblioteca.genero.GeneroRepository;
import com.api.Biblioteca.livro.DadosAlteracaoLivro;
import com.api.Biblioteca.livro.DadosCadastroLivro;
import com.api.Biblioteca.livro.DadosListagemLivro;
import com.api.Biblioteca.livro.Livro;
import com.api.Biblioteca.livro.LivroRepository;

import jakarta.transaction.Transactional;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/livros")
public class LivroController {

	@Autowired
	LivroRepository LivroRepository;
	
	@Autowired
	GeneroRepository GeneroRepository;

	@Autowired
	AutorRepository AutorRepository;
	

	@PostMapping
	@Transactional
	public ResponseEntity<?> PublicarLivro(@RequestBody DadosCadastroLivro dados) {
		Genero genero = GeneroRepository.findById(dados.id_genero()).orElse(null);
		if(genero == null) {
			return ResponseEntity.notFound().build();
		}
		Autor autor = AutorRepository.findById(dados.id_autor()).orElse(null);
		if(autor == null) {
			return ResponseEntity.notFound().build();
		}
		Livro livro = LivroRepository.save(new Livro(dados, genero, autor));
		Long id = livro.getId();
		URI location = ServletUriComponentsBuilder.fromCurrentContextPath().path("/{id}").buildAndExpand(id)
				.toUri();
		return ResponseEntity.created(location).build();

	}

	
	@GetMapping
	public ResponseEntity<List<DadosListagemLivro>> listar() {
		var lista = LivroRepository.findAll().stream().map(DadosListagemLivro::new).toList();
		return ResponseEntity.ok(lista);

	}

	
	@PutMapping
	@Transactional
	public ResponseEntity<?> AtualizaLivro(@RequestBody DadosAlteracaoLivro dados) {
		if (!LivroRepository.existsById(dados.id())) {
			return ResponseEntity.badRequest().body("Livro não encontrado");
		}
		Genero genero = GeneroRepository.findById(dados.id_genero()).orElse(null);
		if(genero == null) {
			return ResponseEntity.notFound().build();
		}
		Autor autor = AutorRepository.findById(dados.id_autor()).orElse(null);
		if(autor == null) {
			return ResponseEntity.notFound().build();
		}
		var livro = LivroRepository.getReferenceById(dados.id());
		livro.atualizaInformacoes(dados, genero, autor);
		return ResponseEntity.ok(livro);

	}

	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> excluirLivro(@PathVariable Long id) {
		if (!LivroRepository.existsById(id)) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Livro não encontrada");
		}
		LivroRepository.deleteById(id);
		return ResponseEntity.noContent().build();

	}

	
	@GetMapping("/{id}")
	public ResponseEntity<?> detalhar(@PathVariable Long id) {
		if (!LivroRepository.existsById(id)) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Livro não encontrado");
		}
		var livro = LivroRepository.getReferenceById(id);
		DadosListagemLivro dados = new DadosListagemLivro(livro);
		return ResponseEntity.ok(dados);
	}
	
	@GetMapping("/status")
	public ResponseEntity<List<DadosListagemLivro>> listarStatus() {
		var lista = LivroRepository.findByStatusContaining("DISPONIVEL").stream().map(DadosListagemLivro::new).toList();
		return ResponseEntity.ok(lista);

	}


}
