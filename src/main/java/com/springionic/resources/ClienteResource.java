package com.springionic.resources;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springionic.domain.Cliente;
import com.springionic.dto.ClienteDTO;
import com.springionic.services.ClienteService;

@RestController
@RequestMapping(value = "/clientes")
public class ClienteResource {
	
	@Autowired
	private ClienteService service;
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Cliente> find(@PathVariable Integer id) {		
		Cliente obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@Valid @RequestBody ClienteDTO objDto, @PathVariable Integer id){
		//convertendo o objDto em um obj do tipo Cliente
		Cliente obj = service.fromDTO(objDto);
		
		//verificando se é a Cliente correta
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
	public ResponseEntity<List<ClienteDTO>> findAll() {
		//criando uma lista com todas Clientes
		List<Cliente> list = service.findAll();
		
		//percorrendo a lista e instanciando o DTO para cada elemento da lista | collect para transformar o resultado para o tipo List
		List<ClienteDTO> listDTO =  list.stream().map(obj -> new ClienteDTO(obj)).collect(Collectors.toList());		
		
		return ResponseEntity.ok().body(listDTO);
	}
	
	//endpoint para chamar o método de paginação
	@RequestMapping(value = "/page", method = RequestMethod.GET)
	public ResponseEntity<Page<ClienteDTO>> findPage(
			@RequestParam(value = "page", defaultValue = "0") Integer page, 
			@RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage, 
			@RequestParam(value = "orderBy", defaultValue = "nome") String orderBy, 
			@RequestParam(value = "direction", defaultValue = "ASC") String direction) {
		
		Page<Cliente> list = service.findPage(page, linesPerPage, orderBy, direction);
		
		Page<ClienteDTO> listDTO =  list.map(obj -> new ClienteDTO(obj));		
		
		return ResponseEntity.ok().body(listDTO);
	}


}
