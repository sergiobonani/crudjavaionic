package com.sergiobonani.crudjavaionic.domain.enums;

public enum TipoCliente {
	PESSOAFISICA(1, "Pessoa Física"),
	PESSOAJURIDICA(2, "Pessoa Jurídica");

	private int cod;
	private String descricao;

	public int getCod() {
		return cod;
	}

	public String getDescricao() {
		return descricao;
	}

	public static TipoCliente toEnum(Integer cod){
		if(cod == null){
			return null;
		}

		for (TipoCliente tipo: TipoCliente.values()){
			if (cod.equals(tipo.getCod())){
				return tipo;
			}
		}

		throw new IllegalArgumentException("Id inválido: " + cod);
	}

	TipoCliente(int cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}
}
