package com.sergiobonani.crudjavaionic.resources;

import com.sergiobonani.crudjavaionic.domain.Categoria;
import com.sergiobonani.crudjavaionic.services.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Handler;

@RestController
@RequestMapping(value = "/categorias")
public class CategoriaResource {

	@Autowired
	private CategoriaService service;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> find(@PathVariable Integer id){


		Categoria categoria = service.buscar(id);

		return ResponseEntity.ok().body(categoria);
	}
}
