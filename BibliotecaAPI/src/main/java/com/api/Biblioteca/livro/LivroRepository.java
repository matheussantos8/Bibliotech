package com.api.Biblioteca.livro;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LivroRepository extends JpaRepository<Livro, Long>{
	List<Livro> findByStatusContaining(String status);
}

