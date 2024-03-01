package com.example.demo.modelo;

public class Likes {
	int idUser;
	int idLiked;
	public Likes(int idUser, int idLiked) {
		super();
		this.idUser = idUser;
		this.idLiked = idLiked;
	}
	public Likes() {
		super();
	}
	
	public int getIdUser() {
		return idUser;
	}
	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}
	public int getIdLiked() {
		return idLiked;
	}
	public void setIdLiked(int idLiked) {
		this.idLiked = idLiked;
	}
	@Override
	public String toString() {
		return "Likes [idUser=" + idUser + ", idLiked=" + idLiked + "]";
	}
	
	
}
