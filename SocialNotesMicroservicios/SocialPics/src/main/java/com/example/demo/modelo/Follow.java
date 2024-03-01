package com.example.demo.modelo;

public class Follow {
	int idUser;
	int idFollow;
	
	public Follow() {
		super();
	}
	public Follow(int idUser, int idFollow) {
		super();
		this.idUser = idUser;
		this.idFollow = idFollow;
	}
	public int getIdUser() {
		return idUser;
	}
	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}
	public int getIdFollow() {
		return idFollow;
	}
	public void setIdFollow(int idFollow) {
		this.idFollow = idFollow;
	}
	@Override
	public String toString() {
		return "Likes [idUser=" + idUser + ", idLiked=" + idFollow + "]";
	}
}
