package com.sergiobonani.crudjavaionic.dto;

import com.sergiobonani.crudjavaionic.domain.Cliente;
import com.sergiobonani.crudjavaionic.services.validation.ClienteUpdate;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@ClienteUpdate
public class ClienteDTO implements Serializable {
	private Integer id;

	@NotEmpty(message = "Preenchimento obrigatório")
	@Length(min = 5, max = 120, message = "O tamanho deve ser entre 5 e 120 caracteres")
	private String nome;

	@NotEmpty(message = "Preenchimento obrigatório")
	@Email(message = "E-mail inválido")
	private String email;

	public ClienteDTO(){

	}

	public ClienteDTO(Integer id, String nome, String email) {
		id = id;
		this.nome = nome;
		this.email = email;
	}

	public ClienteDTO(Cliente entity){
		id = entity.getId();
		nome = entity.getNome();
		email = entity.getEmail();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
