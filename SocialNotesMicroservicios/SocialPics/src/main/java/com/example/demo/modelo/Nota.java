package com.example.demo.modelo;

import java.util.List;

public class Nota {
	private int idnota;
	private int idUser;
	private List <Likes> likes;
	private String mensaje;

	public Nota() {
		super();
	}

	public Nota(int idUser, List<Likes> likes, String mensaje) {
		super();
		this.idUser = idUser;
		this.likes = likes;
		this.mensaje = mensaje;
	}

	public Nota(int idnota, int idUser, List<Likes> likes, String mensaje) {
		super();
		this.idnota = idnota;
		this.idUser = idUser;
		this.likes = likes;
		this.mensaje = mensaje;
	}

	public int getIdnota() {
		return idnota;
	}

	public void setIdnota(int idnota) {
		this.idnota = idnota;
	}

	public int getIdUser() {
		return idUser;
	}

	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}

	public List<Likes> getLikes() {
		return likes;
	}

	public void setLikes(List<Likes> likes) {
		this.likes = likes;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	@Override
	public String toString() {
		return "Nota [idnota=" + idnota + ", idUser=" + idUser + ", likes=" + likes + ", mensaje=" + mensaje + "]";
	}
	
	
	
}
