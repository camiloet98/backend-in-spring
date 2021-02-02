package com.example.practicaBackend.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="photo")
public class Photo {
	@Id
	private int id;
	private String foto;
	
	public String getFoto() {
		return foto;
	}
	public void setFoto(String foto) {
		this.foto = foto;
	}
	public int getIdentificacion() {
		return id;
	}
	public void setIdentificacion(int identificacion) {
		this.id = identificacion;
	}
}
