package com.springionic.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springionic.domain.Categoria;
import com.springionic.repositories.CategoriaRepository;
import com.springionic.services.exceptions.ObjectNotFoundException;


@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository repo;
	
	public Categoria findById(Integer id) {
		Optional<Categoria> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Categoria.class.getName()));
	}

	public Categoria insert(Categoria obj) {
		// verificando se é um obj novo
		obj.setId(null);
		return repo.save(obj);
	}

	public Categoria update(Categoria obj) {
		//verificando se o obj não está vazio
		findById(obj.getId());
		return repo.save(obj);
	}

}
