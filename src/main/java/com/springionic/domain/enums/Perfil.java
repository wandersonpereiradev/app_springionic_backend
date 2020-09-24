package com.springionic.domain.enums;

public enum Perfil {
	
	ADMIN(1, "ROLE_ADMIN"),
	CLIENTE(2, "ROLE_CLIENTE");
	
	private int cod;
	private String descricao;
	
	private Perfil(int cod, String descricao) {
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
	public static Perfil toEnum(Integer cod) {
		
		if (cod == null) {
			return null;
		}
		
		//percorrendo todos os valores possíveis do TipoCliente
		for (Perfil x : Perfil.values()) {
			
			//verificando se o parâmetro recebido corresponde ao código TipoCliente cadastrado
			if (cod.equals(x.getCod())) {
				return x;
			}
		}
		
		//lançando uma exceção caso o parâmetro não corresponda a nenhum código
		throw new IllegalArgumentException("ID inválido " + cod);
	}

}
