package com.sergiobonani.crudjavaionic.resources;

import com.sergiobonani.crudjavaionic.domain.Cliente;
import com.sergiobonani.crudjavaionic.dto.ClienteDTO;
import com.sergiobonani.crudjavaionic.dto.ClienteNewDTO;
import com.sergiobonani.crudjavaionic.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/clientes")
public class ClienteResource {

	@Autowired
	private ClienteService service;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Cliente> find(@PathVariable Integer id){
		Cliente cliente = service.find(id);

		return ResponseEntity.ok().body(cliente);
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody ClienteNewDTO clienteDto){
		Cliente cliente = service.insert(service.fromDTO(clienteDto));
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(cliente.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@RequestMapping(value = "/{id}",method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@PathVariable Integer id,@Valid @RequestBody ClienteDTO clienteDto){
		Cliente cliente = service.fromDTO(clienteDto);
		cliente.setId(id);
		cliente = service.update(cliente);

		return ResponseEntity.noContent().build();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id){
		service.delete(id);

		return ResponseEntity.noContent().build();
	}

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<ClienteDTO>> findAll(){
		List<Cliente> clientes = service.findAll();
		List<ClienteDTO> listDto = clientes.stream().map(c -> new ClienteDTO(c)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);
	}

	@RequestMapping(value = "/page", method = RequestMethod.GET)
	public ResponseEntity<Page<ClienteDTO>> findPage(@RequestParam(value = "page", defaultValue = "0") Integer page,
													   @RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
													   @RequestParam(value = "orderBy", defaultValue = "nome") String orderBy,
													   @RequestParam(value = "direction", defaultValue = "ASC") String direction){
		Page<Cliente> clientes = service.findPage(page, linesPerPage, orderBy, direction);
		Page<ClienteDTO> listDto = clientes.map(c -> new ClienteDTO(c));
		return ResponseEntity.ok().body(listDto);
	}
}
