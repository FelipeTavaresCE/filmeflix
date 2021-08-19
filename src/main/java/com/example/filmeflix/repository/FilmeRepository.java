package com.example.filmeflix.repository;

import com.example.filmeflix.model.Filme;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FilmeRepository extends MongoRepository<Filme, String>{
	
}
