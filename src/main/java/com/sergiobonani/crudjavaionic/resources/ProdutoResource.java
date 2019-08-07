package com.sergiobonani.crudjavaionic.resources;

import com.sergiobonani.crudjavaionic.domain.Produto;
import com.sergiobonani.crudjavaionic.dto.ProdutoDTO;
import com.sergiobonani.crudjavaionic.resources.utils.URL;
import com.sergiobonani.crudjavaionic.services.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/produtos")
public class ProdutoResource {

	@Autowired
	private ProdutoService service;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Produto> find(@PathVariable Integer id){
		Produto produto = service.find(id);

		return ResponseEntity.ok().body(produto);
	}

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<Page<ProdutoDTO>> findPage(@RequestParam(value = "nome", defaultValue = "0") String nome,
													 @RequestParam(value = "categorias", defaultValue = "0") String categorias,
													 @RequestParam(value = "page", defaultValue = "0") Integer page,
													 @RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
													 @RequestParam(value = "orderBy", defaultValue = "nome") String orderBy,
													 @RequestParam(value = "direction", defaultValue = "ASC") String direction){
		String nomeDecoded = URL.decodeParam(nome);
		List<Integer> ids = URL.decodeIntList(categorias);

		Page<Produto> produtos = service.search(nomeDecoded, ids, page, linesPerPage, orderBy, direction);
		Page<ProdutoDTO> listDto = produtos.map(c -> new ProdutoDTO(c.getId(), c.getNome(), c.getPreco()));
		return ResponseEntity.ok().body(listDto);
	}
}
