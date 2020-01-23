package com.daniel.biblioteca.json.requisicao;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class RequisicaoCategoria {

	@NotNull
	private String nome;

	private String descricao;

	private String categoriaMae;

}
