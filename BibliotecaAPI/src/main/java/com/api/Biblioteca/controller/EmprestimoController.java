package com.api.Biblioteca.controller;

import java.net.URI;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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

import com.api.Biblioteca.pessoa.Pessoa;
import com.api.Biblioteca.pessoa.PessoaRepository;

import jakarta.transaction.Transactional;

import com.api.Biblioteca.emprestimo.DadosAlteracaoEmprestimo;
import com.api.Biblioteca.emprestimo.DadosCadastroEmprestimo;
import com.api.Biblioteca.emprestimo.DadosListagemEmprestimo;

import com.api.Biblioteca.emprestimo.EmprestimoRepository;
import com.api.Biblioteca.emprestimo.Empréstimo;
import com.api.Biblioteca.livro.Livro;
import com.api.Biblioteca.livro.LivroRepository;

@RestController
@RequestMapping("/emprestimos")

public class EmprestimoController {

	@Autowired
	private LivroRepository LivroRepository;

	@Autowired
	private PessoaRepository PessoaRepository;

	@Autowired
	private EmprestimoRepository EmprestimoRepository;

	
	@PostMapping
	@Transactional
	public ResponseEntity<?> PublicarEmprestimo(@RequestBody DadosCadastroEmprestimo dados) {
		Livro livro = LivroRepository.findById(dados.id_livro()).orElse(null);
		if(livro == null) {
			return ResponseEntity.notFound().build();
		}
		livro.atualizaStatus("EMPRESTADO");
		LivroRepository.save(livro);

		Pessoa pessoa = PessoaRepository.findById(dados.id_pessoa()).orElse(null);
		if(pessoa == null) {
			return ResponseEntity.notFound().build();
		}

		Empréstimo emprestimo = EmprestimoRepository.save(new Empréstimo(dados, livro, pessoa));
		Long id = emprestimo.getId();
		URI location = ServletUriComponentsBuilder.fromCurrentContextPath().path("/{id}")
				.buildAndExpand(id).toUri();
		return ResponseEntity.created(location).build();

	}

	
	@GetMapping
	public ResponseEntity<List<DadosListagemEmprestimo>> listar() {
		var lista = EmprestimoRepository.findAll().stream().map(DadosListagemEmprestimo::new).toList();
		return ResponseEntity.ok(lista);
	}

	
	@PutMapping
	@Transactional
	public ResponseEntity<?> AtualizarEmprestimo(@RequestBody DadosAlteracaoEmprestimo dados) {
		if (!EmprestimoRepository.existsById(dados.id())) {
			return ResponseEntity.badRequest().body("Empréstimo não encontrado");
		}
		Livro livro = LivroRepository.findById(dados.id_livro()).orElse(null);
		if(livro == null) {
			return ResponseEntity.notFound().build();
		}
		Pessoa pessoa = PessoaRepository.findById(dados.id_pessoa()).orElse(null);
		if(pessoa == null) {
			return ResponseEntity.notFound().build();
		}
		var emprestimo = EmprestimoRepository.getReferenceById(dados.id());
		emprestimo.atualizaInformacoes(dados, livro, pessoa);
		return ResponseEntity.ok(emprestimo);

	}

	
	@PutMapping("/{id}/devolver")
	public ResponseEntity<?> devolverEmprestimo(@PathVariable Long id) {
	    Empréstimo emprestimo = EmprestimoRepository.findById(id).orElse(null);
	    if (emprestimo == null) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Empréstimo não encontrado");
	    }
	    LocalDate data = LocalDate.now();
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	    String dataFormatada = data.format(formatter);
	    emprestimo.atualizaDataDevolucao(dataFormatada);
	    Livro livro = LivroRepository.findById(emprestimo.getLivro().getId()).orElse(null);
	    if (livro != null) {
	    	livro.atualizaStatus("DISPONIVEL");
	    	LivroRepository.save(livro);
	    }
	    EmprestimoRepository.save(emprestimo);
	    return ResponseEntity.ok("Devolução registrada com sucesso");

	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<?> atualizaStatus(@PathVariable Long id, @RequestBody DadosStatusEmprestimo dados) {
		if (!EmprestimoRepository.existsById(id)) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Empréstimo não encontrada");
		}
		var status = EmprestimoRepository.getReferenceById(id);
		status.AtualizaStatusEmprestimo(dados);
		return ResponseEntity.ok(status);
	}

	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> excluirEmpréstimo(@PathVariable Long id) {
		if (!EmprestimoRepository.existsById(id)) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Empréstimo não encontrado");
		}
		Empréstimo emprestimo = EmprestimoRepository.getReferenceById(id);
		Livro livro = LivroRepository.getReferenceById(emprestimo.getLivro().getId());
		livro.atualizaStatus("DISPONIVEL");
		LivroRepository.save(livro);

		EmprestimoRepository.deleteById(id);
		return ResponseEntity.noContent().build();
	}

	
	@GetMapping("/{id}")
	public ResponseEntity<?> detalhar(@PathVariable Long id) {
		if (!EmprestimoRepository.existsById(id)) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Empréstimo não encontrado");
		}
		var emprestimo = EmprestimoRepository.getReferenceById(id);
		DadosListagemEmprestimo dados = new DadosListagemEmprestimo(emprestimo);
		return ResponseEntity.ok(dados);
	}
}
