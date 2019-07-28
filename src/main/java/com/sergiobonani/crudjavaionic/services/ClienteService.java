package com.sergiobonani.crudjavaionic.services;

import com.sergiobonani.crudjavaionic.domain.Cidade;
import com.sergiobonani.crudjavaionic.domain.Cliente;
import com.sergiobonani.crudjavaionic.domain.Endereco;
import com.sergiobonani.crudjavaionic.domain.enums.TipoCliente;
import com.sergiobonani.crudjavaionic.dto.ClienteDTO;
import com.sergiobonani.crudjavaionic.dto.ClienteNewDTO;
import com.sergiobonani.crudjavaionic.exceptions.DataIntegrityException;
import com.sergiobonani.crudjavaionic.exceptions.ObjectNotFoundException;
import com.sergiobonani.crudjavaionic.repositories.CidadeRepository;
import com.sergiobonani.crudjavaionic.repositories.ClienteRepository;
import com.sergiobonani.crudjavaionic.repositories.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {
	@Autowired
	private ClienteRepository repository;

	@Autowired
	private CidadeRepository cidadeRepository;

	@Autowired
	private EnderecoRepository enderecoRepository;

	public Cliente find(Integer id){
		Optional<Cliente> cliente = repository.findById(id);
		return cliente.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));
	}

	@Transactional
	public Cliente insert(Cliente cliente){
		cliente.setId(null);
		repository.save(cliente);
		enderecoRepository.saveAll(cliente.getEnderecos());
		return cliente;
	}

	public Cliente update(Cliente cliente){
		Cliente newObj = find(cliente.getId());
		updateData(newObj, cliente);
		return repository.save(newObj);
	}

	private void updateData(Cliente newObj, Cliente obj){
		newObj.setNome(obj.getNome());
		newObj.setEmail(obj.getEmail());
	}

	public void delete(Integer id){
		find(id);
		try {
			repository.deleteById(id);
		}catch (DataIntegrityViolationException e){
			throw  new DataIntegrityException("Não é possível excluir o cliente pois há entidades relacionadas");
		}
	}

	public List<Cliente> findAll(){
		return repository.findAll();
	}

	public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
		return repository.findAll(pageRequest);
	}

	public Cliente fromDTO(ClienteDTO dto){
		return new Cliente(dto.getId(), dto.getNome(), dto.getEmail(), null, null);
	}

	public Cliente fromDTO(ClienteNewDTO dto){
		Cliente cliente = new Cliente(null, dto.getNome(), dto.getEmail(), dto.getCpfOuCnpj(), TipoCliente.toEnum(dto.getTipo()));
		Cidade cidade = cidadeRepository.getOne(dto.getCidadeId());
		Endereco endereco = new Endereco(null, dto.getLogradouro(), dto.getNumero(), dto.getComplemento(), dto.getBairro(), dto.getCep(), cliente, cidade);

		cliente.getEnderecos().add(endereco);
		cliente.getTelefones().add(dto.getTelefone1());

		if (dto.getTelefone2() != null){
			cliente.getTelefones().add(dto.getTelefone2());
		}

		if (dto.getTelefone3() != null){
			cliente.getTelefones().add(dto.getTelefone3());
		}

		return cliente;
	}
}
