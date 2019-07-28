package com.sergiobonani.crudjavaionic.services;

import com.sergiobonani.crudjavaionic.domain.Categoria;
import com.sergiobonani.crudjavaionic.exceptions.DataIntegrityException;
import com.sergiobonani.crudjavaionic.exceptions.ObjectNotFoundException;
import com.sergiobonani.crudjavaionic.repositories.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {
	@Autowired
	private CategoriaRepository repository;

	public Categoria find(Integer id){
		Optional<Categoria> categoria = repository.findById(id);
		return categoria.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Categoria.class.getName()));
	}

	public Categoria insert(Categoria categoria){
		categoria.setId(null);
		return repository.save(categoria);
	}

	public Categoria update(Categoria categoria){
		return repository.save(categoria);
	}

	public void delete(Integer id){
		find(id);
		try {
			repository.deleteById(id);
		}catch (DataIntegrityViolationException e){
			throw  new DataIntegrityException("Não é possível excluir uma categoria vinculada a um produto");
		}
	}

	public List<Categoria> findAll(){
		return repository.findAll();
	}
}
