package com.sergiobonani.crudjavaionic.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Categoria implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String nome;

	@ManyToMany(mappedBy = "categorias")
	private List<Produto> produtos = new ArrayList<>();

	public Categoria(){

	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Categoria categoria = (Categoria) o;
		return id.equals(categoria.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	public Categoria(Integer id, String nome){
		super();
		this.id = id;
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}
}
