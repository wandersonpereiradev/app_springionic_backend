package com.springionic.domain.enums;

public enum EstadoPagamento {
	
	PENDENTE(1, "Pendente"),
	QUITADO(2, "Quitado"),
	CANCELADO(3, "Cancelado");
	
	private int cod;
	private String descricao;
	
	private EstadoPagamento(int cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}
	
	public int getCod() {
		return cod;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	//método que recebe o código e devolve um objeto TipoCliente
	public static EstadoPagamento toEnum(Integer cod) {
		
		if (cod == null) {
			return null;
		}
		
		//percorrendo todos os valores possíveis do TipoCliente
		for (EstadoPagamento x : EstadoPagamento.values()) {
			
			//verificando se o parâmetro recebido corresponde ao código TipoCliente cadastrado
			if (cod.equals(x.getCod())) {
				return x;
			}
		}
		
		//lançando uma exceção caso o parâmetro não corresponda a nenhum código
		throw new IllegalArgumentException("ID inválido " + cod);
	}

}
