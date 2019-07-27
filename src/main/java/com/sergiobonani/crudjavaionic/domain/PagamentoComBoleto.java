package com.sergiobonani.crudjavaionic.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sergiobonani.crudjavaionic.domain.enums.EstadoPagamento;

import javax.persistence.Entity;
import java.util.Date;

@Entity
public class PagamentoComBoleto extends Pagamento {

	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
	private Date dataVenciento;
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
	private Date dataPagamento;

	public Date getDataVenciento() {
		return dataVenciento;
	}

	public void setDataVenciento(Date dataVenciento) {
		this.dataVenciento = dataVenciento;
	}

	public Date getDataPagamento() {
		return dataPagamento;
	}

	public void setDataPagamento(Date dataPagamento) {
		this.dataPagamento = dataPagamento;
	}

	public PagamentoComBoleto() {
	}

	public PagamentoComBoleto(Integer id, EstadoPagamento estadoPagamento, Pedido pedido, Date dataVenciento, Date dataPagamento) {
		super(id, estadoPagamento.getCod(), pedido);
		this.dataVenciento = dataVenciento;
		this.dataPagamento = dataPagamento;
	}
}
