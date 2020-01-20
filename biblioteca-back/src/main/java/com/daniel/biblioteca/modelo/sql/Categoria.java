package com.daniel.biblioteca.modelo.sql;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

@Entity
@Table(schema = "biblioteca", name = "categorias")
public class Categoria extends BaseModelSql{

	private static final long serialVersionUID = -4444617492132534748L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "nome")
	private String nome;

	@Column(name = "descricao")
	private String descricao;

	@Column(name = "id_categoria_mae")
	private Long idCategoriaMae;

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
            return true;
		}
	    if (!(obj instanceof Categoria)) {
	            return false;
	    }
	    Categoria other = (Categoria) obj;
	    return Objects.equals(id, other.id);
	}
}
