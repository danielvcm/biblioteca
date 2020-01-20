package com.daniel.biblioteca.modelo.sql;

import javax.persistence.MappedSuperclass;

import com.daniel.biblioteca.modelo.BaseModel;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

@MappedSuperclass
public abstract class BaseModelSql extends BaseModel<Long>{

	private static final long serialVersionUID = 3501640998438978089L;

	public abstract int hashCode();

	public abstract boolean equals(Object obj);

}
