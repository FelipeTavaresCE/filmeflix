package com.example.filmeflix.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.filmeflix.model.Filme;

@Repository
public interface FilmeRepository extends JpaRepository<Filme, Long>{
	
}
