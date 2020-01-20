package com.daniel.biblioteca.modelo;

import java.io.Serializable;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class BaseModel <T extends Serializable> implements Serializable{

	private static final long serialVersionUID = 4357245898618796017L;

}
