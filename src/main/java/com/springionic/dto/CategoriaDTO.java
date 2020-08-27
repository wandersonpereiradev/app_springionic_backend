package com.springionic.dto;

import java.io.Serializable;

import com.springionic.domain.Categoria;

public class CategoriaDTO implements Serializable {	
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String nome;
	
	public CategoriaDTO() {
	}
	
	//construtor responsável por instanciar um DTO à partir de um objeto Categoria
	public CategoriaDTO(Categoria obj) {
		id = obj.getId();
		nome = obj.getNome();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	

}
