package com.sergiobonani.crudjavaionic.domain;

import com.sergiobonani.crudjavaionic.domain.enums.EstadoPagamento;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Pagamento implements Serializable {
	@Id
	private Integer id;

	private Integer estadoPagamento;

	@OneToOne
	@JoinColumn(name = "pedido_id")
	@MapsId
	private Pedido pedido;

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Pagamento pagamento = (Pagamento) o;
		return Objects.equals(id, pagamento.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public EstadoPagamento getEstadoPagamento() {
		return EstadoPagamento.toEnum(estadoPagamento);
	}

	public void setEstadoPagamento(EstadoPagamento estadoPagamento) {
		this.estadoPagamento = estadoPagamento.getCod();
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public Pagamento(){

	}

	public Pagamento(Integer id, Integer estadoPagamento, Pedido pedido) {
		this.id = id;
		this.estadoPagamento = estadoPagamento;
		this.pedido = pedido;
	}
}
