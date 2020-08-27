package com.springionic.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.springionic.domain.Categoria;
import com.springionic.dto.CategoriaDTO;
import com.springionic.services.CategoriaService;

@RestController
@RequestMapping(value = "/categorias")
public class CategoriaResource {
	
	@Autowired
	private CategoriaService service;
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Categoria> find(@PathVariable Integer id) {		
		Categoria obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@RequestMapping(method = RequestMethod.POST) //mapeando o método como um POST | @RequestBody faz o Json ser convertido para o obj Java
	public ResponseEntity<Void> insert(@RequestBody Categoria obj) {
		//salvando o objeto no BD
		obj = service.insert(obj);
		
		//devolvendo o ID como argumento da URI
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		
		//retornando a URI com o status HTTP Created
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@RequestBody Categoria obj, @PathVariable Integer id){
		//verificando se é a Categoria correta
		obj.setId(id);
		obj = service.update(obj);
		
		//a resposta será um conteúdo vazio
		return ResponseEntity.noContent().build();		
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<CategoriaDTO>> findAll() {
		//criando uma lista com todas Categorias
		List<Categoria> list = service.findAll();
		
		//percorrendo a lista e instanciando o DTO para cada elemento da lista | collect para transformar o resultado para o tipo List
		List<CategoriaDTO> listDTO =  list.stream().map(obj -> new CategoriaDTO(obj)).collect(Collectors.toList());		
		
		return ResponseEntity.ok().body(listDTO);
	}

}
