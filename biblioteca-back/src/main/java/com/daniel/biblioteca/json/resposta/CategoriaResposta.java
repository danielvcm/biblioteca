package com.daniel.biblioteca.json.resposta;

import java.util.List;

import lombok.Data;

@Data
public class CategoriaResposta {

	private Long id;

	private String nome;

	private String descricao;

	private List<CategoriaResposta> categoriasFilhas;

}
