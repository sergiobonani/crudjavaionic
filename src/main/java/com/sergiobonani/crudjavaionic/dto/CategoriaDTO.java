package com.sergiobonani.crudjavaionic.dto;

import com.sergiobonani.crudjavaionic.domain.Categoria;

import java.io.Serializable;

public class CategoriaDTO implements Serializable {
	private Integer id;
	private String nome;

	public CategoriaDTO(){

	}

	public CategoriaDTO(Categoria entity){
		id = entity.getId();
		nome = entity.getNome();
	}

	public CategoriaDTO(Integer id, String nome) {
		this.id = id;
		this.nome = nome;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
}
