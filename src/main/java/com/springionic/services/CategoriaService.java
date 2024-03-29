package com.springionic.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.springionic.domain.Categoria;
import com.springionic.dto.CategoriaDTO;
import com.springionic.repositories.CategoriaRepository;
import com.springionic.services.exceptions.DataIntegrityException;
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
			
			//instanciando o newObj à partir do BD | verificando se o obj não está vazio
			Categoria newObj = findById(obj.getId());
			
			//método auxiliar para validar o newObj com base no obj que veio como argumento
			updateData(newObj, obj);
			
			//o save será feito com os dados atualizados e validados
			return repo.save(newObj);
		}

	public void delete(Integer id) {
		//verifcando se o id existe
		findById(id);
		try {
			repo.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir uma categoria que possui produtos");
		}
				
	}

	public List<Categoria> findAll() {		
		return repo.findAll();
	}
	
	//criando paginação para as categorias
	public Page<Categoria> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}
	
	//método auxiliar para converter um objeto CategoriaDTO em um objeto Categoria
	public Categoria fromDTO(CategoriaDTO objDto) {
		return new Categoria(objDto.getId(), objDto.getNome());
	}
	
	//private pq é um método auxiliar dessa classe apenas
	private void updateData(Categoria newObj, Categoria obj) {
		newObj.setNome(obj.getNome());
	}

}
