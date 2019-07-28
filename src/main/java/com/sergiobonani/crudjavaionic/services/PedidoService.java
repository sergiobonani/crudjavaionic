package com.sergiobonani.crudjavaionic.services;

import com.sergiobonani.crudjavaionic.domain.Categoria;
import com.sergiobonani.crudjavaionic.domain.Pedido;
import com.sergiobonani.crudjavaionic.exceptions.ObjectNotFoundException;
import com.sergiobonani.crudjavaionic.repositories.CategoriaRepository;
import com.sergiobonani.crudjavaionic.repositories.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PedidoService {
	@Autowired
	private PedidoRepository repository;

	public Pedido find(Integer id){
		Optional<Pedido> categoria = repository.findById(id);
		return categoria.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Pedido.class.getName()));
	}
}
