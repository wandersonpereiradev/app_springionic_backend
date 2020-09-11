package com.springionic.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.springionic.domain.Cidade;
import com.springionic.domain.Cliente;
import com.springionic.domain.Endereco;
import com.springionic.domain.enums.TipoCliente;
import com.springionic.dto.ClienteDTO;
import com.springionic.dto.ClienteNewDTO;
import com.springionic.repositories.ClienteRepository;
import com.springionic.repositories.EnderecoRepository;
import com.springionic.services.exceptions.DataIntegrityException;
import com.springionic.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repo;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	

	public Cliente findById(Integer id) {
		Optional<Cliente> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));
	}

	@Transactional
	public Cliente insert(Cliente obj) {
		// verificando se é um obj novo
		obj.setId(null);
		obj = repo.save(obj);
		enderecoRepository.saveAll(obj.getEnderecos());
		return obj;
	}

	// só é permitido atualização de nome e e-mail
	public Cliente update(Cliente obj) {

		// instanciando o newObj à partir do BD | verificando se o obj não está vazio
		Cliente newObj = findById(obj.getId());

		// método auxiliar para validar o newObj com base no obj que veio como argumento
		updateData(newObj, obj);

		// o save será feito com os dados atualizados e validados
		return repo.save(newObj);
	}

	public void delete(Integer id) {
		// verifcando se o id existe
		findById(id);
		try {
			repo.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir um cliente que possui pedidos");
		}

	}

	public List<Cliente> findAll() {
		return repo.findAll();
	}

	// criando paginação para as categorias
	public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}

	// método auxiliar para converter um objeto ClienteDTO em um objeto Cliente
	public Cliente fromDTO(ClienteDTO objDto) {
		return new Cliente(objDto.getId(), objDto.getNome(), objDto.getEmail(), null, null);
	}
	
	public Cliente fromDTO(ClienteNewDTO objDto) {
		Cliente cli = new Cliente(null, objDto.getNome(), objDto.getEmail(), objDto.getCpfOuCnpj(), TipoCliente.toEnum(objDto.getTipo()));
		Cidade cid = new Cidade(objDto.getCidadeId(), null, null);
		Endereco end = new Endereco(null, objDto.getLogradouro(), objDto.getNumero(), objDto.getComplemento(), objDto.getBairro(), objDto.getCep(), cli, cid);
		cli.getEnderecos().add(end);
		cli.getTelefones().add(objDto.getTelefone1());
		if (objDto.getTelefone2()!=null) {
			cli.getTelefones().add(objDto.getTelefone2());
		}
		if (objDto.getTelefone3()!=null) {
			cli.getTelefones().add(objDto.getTelefone3());
		}
		return cli;
	}

	// private pq é um método auxiliar dessa classe apenas
	private void updateData(Cliente newObj, Cliente obj) {
		newObj.setNome(obj.getNome());
		newObj.setEmail(obj.getEmail());
	}

}
