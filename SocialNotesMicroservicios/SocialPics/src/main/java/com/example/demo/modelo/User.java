package com.example.demo.modelo;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class User {
	private int idUser;
	private String nombreUser;
	private String email;
	private String pass;
	private List<Follow>seguidos;
	private List <Follow> seguidores;
	
	
	
	public User() {
		super();
	}



	public User(String nombreUser, String email, String pass, List<Follow> seguidos, List<Follow> seguidores) {
		super();
		this.nombreUser = nombreUser;
		this.email = email;
		this.pass = pass;
		this.seguidos = seguidos;
		this.seguidores = seguidores;
	}



	public User(int idUser, String nombreUser, String email, String pass, List<Follow> seguidos,
			List<Follow> seguidores) {
		super();
		this.idUser = idUser;
		this.nombreUser = nombreUser;
		this.email = email;
		this.pass = pass;
		this.seguidos = seguidos;
		this.seguidores = seguidores;
	}



	public int getIdUser() {
		return idUser;
	}



	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}



	public String getNombreUser() {
		return nombreUser;
	}



	public void setNombreUser(String nombreUser) {
		this.nombreUser = nombreUser;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public String getPass() {
		return pass;
	}



	public void setPass(String pass) {
		this.pass = pass;
	}



	public List<Follow> getSeguidos() {
		return seguidos;
	}



	public void setSeguidos(List<Follow> seguidos) {
		this.seguidos = seguidos;
	}



	public List<Follow> getSeguidores() {
		return seguidores;
	}



	public void setSeguidores(List<Follow> seguidores) {
		this.seguidores = seguidores;
	}



	@Override
	public String toString() {
		return "User [idUser=" + idUser + ", nombreUser=" + nombreUser + ", email=" + email + ", pass=" + pass
				+ ", seguidos=" + seguidos + ", seguidores=" + seguidores + "]";
	}
	
	
}
