package com.example.demo.controladores;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.modelo.Follow;
import com.example.demo.modelo.Likes;
import com.example.demo.modelo.Nota;
import com.example.demo.modelo.User;
import com.example.demo.servicios.FirebaseInitializer;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;

@RestController
public class UserController {
	
	 
	@Autowired
	FirebaseInitializer db;
	
	@GetMapping("/main/")
	public String homeApi() {
		 return "funciona";
	}
	
	
	public List<User> getUsers() throws InterruptedException, ExecutionException{
		List<User> listaUsers= new ArrayList<User>();
		CollectionReference usuario=db.getFirebase().collection("User");
		ApiFuture<QuerySnapshot> querySnapshot=usuario.get();
		for(DocumentSnapshot doc: querySnapshot.get().getDocuments()) {
			User u=doc.toObject(User.class);
			listaUsers.add(u);
		}
		return listaUsers;
	}
	
	public List<Nota> getNotas() throws InterruptedException, ExecutionException{
		List<Nota> listaNotas= new ArrayList<Nota>();
		CollectionReference usuario=db.getFirebase().collection("Nota");
		ApiFuture<QuerySnapshot> querySnapshot=usuario.get();
		for(DocumentSnapshot doc: querySnapshot.get().getDocuments()) {
			Nota u=doc.toObject(Nota.class);
			listaNotas.add(u);
		}
		return listaNotas;
	}
	
	public List<Nota> getNotasById(int id) throws InterruptedException, ExecutionException{
		List<Nota> listaNotas= new ArrayList<Nota>();
		CollectionReference usuario=db.getFirebase().collection("Nota");
		ApiFuture<QuerySnapshot> querySnapshot=usuario.get();
		for(DocumentSnapshot doc: querySnapshot.get().getDocuments()) {
			Nota u=doc.toObject(Nota.class);
			if(u.getIdUser()==id) {
				listaNotas.add(u);
			}
		}
		return listaNotas;
	}
	
	public Nota getNota(int idNota) throws InterruptedException, ExecutionException {
		List<Nota> notazas=getNotas();
		Nota na=new Nota();
		for(Nota n:notazas) {
			if(n.getIdnota()==idNota) {
				na=n;
			}
		}
		return na;
	}
	@PostMapping("/registrar/{nombreUser}/{email}/{pass}/")
	public String registrarUser(@PathVariable String nombreUser,@PathVariable String email, @PathVariable String pass) throws IOException, InterruptedException, ExecutionException{
		User usuario=new User();
		boolean repetido=false;
		List<User> listaUsers=getUsers();
		List<Follow> seguidos1=new ArrayList<>();
		List<Follow> seguidores1=new ArrayList<>();
		int idUser=listaUsers.size();
		usuario.setIdUser(idUser);
		usuario.setNombreUser(nombreUser);
		usuario.setEmail(email);
		usuario.setPass(pass);
		usuario.setSeguidores(seguidores1);
		usuario.setSeguidos(seguidos1);
		for(User u:listaUsers) {
			if(u.getNombreUser().equals(nombreUser)) {
				repetido=true;
			}
		}
		
		if(repetido) {
			return "repetido";
		}else {
			CollectionReference u=db.getFirebase().collection("User");
			u.document(String.valueOf(usuario.getIdUser())).set(usuario);
			return "registrado";
		}
		
	}	
	
	@PostMapping("/login/{nombreUser}/{pass}/")
	public String log(@PathVariable String nombreUser,@PathVariable String pass) throws InterruptedException, ExecutionException {
		System.out.println("/login/{nombreUser}/{pass}");
		System.out.println("/login/{"+nombreUser+"}/{"+pass+"}");
		
		List<User> listaUsers=getUsers();
		String idUser="incorrectas";
		for(User u:listaUsers) {
			if(u.getNombreUser().equals(nombreUser)&& u.getPass().equals(pass)) {
				idUser=String.valueOf(u.getIdUser());
			}
		}
		System.out.println("iduser: "+idUser);
		return idUser;
	}
	
	@PostMapping("/postnote/{mensaje}/{idUser}/")
	public String post(@PathVariable String mensaje,@PathVariable int idUser) throws InterruptedException, ExecutionException {
		List <Nota> listaNotas=getNotas();
		List<Likes> listalikes=new ArrayList<>();
		
		int id=0;
		for(Nota n:listaNotas) {
			id=n.getIdnota();
		}
		
		int idnota=id+1;
		Nota n=new Nota();
		n.setIdnota(idnota);
		n.setIdUser(idUser);
		n.setMensaje(mensaje);
		n.setLikes(listalikes);
		CollectionReference u=db.getFirebase().collection("Nota");
		u.document(String.valueOf(n.getIdnota())).set(n);
		return "guardada";
	}
	
	@PostMapping("/getNotas/{idUser}/")
	public List<Nota> getnotas(@PathVariable int idUser) throws InterruptedException, ExecutionException{
		List<Nota> listaNotas=getNotasById(idUser);
		return listaNotas;
	}
	
	
	
	@PostMapping("/getUser/{idUser}/")
	public User getUser(@PathVariable int idUser) throws InterruptedException, ExecutionException {
		List <User> users=getUsers();
		User u1=new User();
		for(User u:users) {
			if(u.getIdUser()==idUser) {
				u1=u;
			}
		}
		return u1;
	}
	
	public User auxGetUser(int idUser) throws InterruptedException, ExecutionException {
		List <User> users=getUsers();
		User u1=new User();
		for(User u:users) {
			if(u.getIdUser()==idUser) {
				u1=u;
			}
		}
		return u1;
	}
	
	@PostMapping("/listarUsers/{idUser}/")
	public List<User> listarUsers(@PathVariable int idUser) throws InterruptedException, ExecutionException{
		List <User> listaUsers=getUsers();
		List <User> listaSinUserActual=new ArrayList<>();
		
		for (User u: listaUsers) {
			if(u.getIdUser()!=idUser && u.getIdUser()!=0) {
				listaSinUserActual.add(u);
			}
		}
		return listaSinUserActual;
	}
	
	@PostMapping("/follow/{idUser}/{idSeguido}/")
	public void follow(@PathVariable int idUser,@PathVariable int idSeguido) throws InterruptedException, ExecutionException {
		Follow f=new Follow(idUser,idSeguido);
		Follow f2=new Follow(idSeguido,idUser);
		User u=auxGetUser(idUser);
		User u2=auxGetUser(idSeguido);
		
		List<Follow> seguidos1=new ArrayList<>();
		List<Follow> seguidores1=u.getSeguidores();
		if(u.getSeguidos().size()==0) {
			seguidos1.add(f);
		}else {
			seguidos1=u.getSeguidos();
			seguidos1.add(f);
		}
		u.setSeguidos(seguidos1);
		u.setSeguidores(seguidores1);
		
		List<Follow> seguidos2=u2.getSeguidos();
		List<Follow> seguidores2=new ArrayList<>();
		
		if(u2.getSeguidores().size()==0) {
			seguidores2.add(f2);
		}else {
			seguidores2=u2.getSeguidos();
			seguidores2.add(f2);
		}
		
		u2.setSeguidores(seguidores2);
		u2.setSeguidos(seguidos2);
		
		CollectionReference ref=db.getFirebase().collection("User");
		ref.document(String.valueOf(u.getIdUser())).set(u);
		ref.document(String.valueOf(u2.getIdUser())).set(u2);
	}
	
	@PostMapping("/unfollow/{idUser}/{idSeguido}/")
	public void unfollow(@PathVariable int idUser,@PathVariable int idSeguido) throws InterruptedException, ExecutionException {
		Follow f=new Follow(idUser,idSeguido);
		Follow f2=new Follow(idSeguido,idUser);
		User u=auxGetUser(idUser);
		User u2=auxGetUser(idSeguido);
		
		List<Follow> seguidos1=new ArrayList<>();
		List<Follow> seguidores1=u.getSeguidores();
		
		System.out.println(u.getSeguidos().toString());
		System.out.println(f.toString());
		System.out.println(seguidos1.toString());
		if(u.getSeguidos().size()!=0) {
			for(Follow fa:u.getSeguidos()) {
				if(fa.getIdUser()!=f.getIdUser()) {
					seguidos1.add(fa);
				}
			}
		}
		System.out.println(seguidos1.toString());
		u.setSeguidos(seguidos1);
		u.setSeguidores(seguidores1);
		
		List<Follow> seguidos2=u2.getSeguidos();
		List<Follow> seguidores2=new ArrayList<>();
		
		if(u2.getSeguidores().size()!=0) {
			for(Follow fa:u2.getSeguidores()) {
				if(fa.getIdUser()!=f2.getIdUser()) {
					seguidores2.add(fa);
				}
			}
		}
		
		u2.setSeguidores(seguidores2);
		u2.setSeguidos(seguidos2);
		
		CollectionReference ref=db.getFirebase().collection("User");
		ref.document(String.valueOf(u.getIdUser())).set(u);
		ref.document(String.valueOf(u2.getIdUser())).set(u2);
		
	}
	
	@PostMapping("/getHomeNotes/{idUser}/")
	public List<Nota> listar(@PathVariable int idUser) throws InterruptedException, ExecutionException{
		User u=auxGetUser(idUser);
		List<Nota> notas=new ArrayList<>();
		List<Follow> seguidos=u.getSeguidos();
		
		for(Follow f:seguidos) {
			List <Nota> lista= getNotasById(f.getIdFollow()); 
			for(Nota n:lista) {
				notas.add(n);
			}
		}
		
		return notas;
	}
	
	@PostMapping("/likeNota/{idNota}/{idUser}/")
	public void like(@PathVariable int idNota,@PathVariable int idUser) throws InterruptedException, ExecutionException {
		Nota n=getNota(idNota);
		List<Likes>likes=n.getLikes();
		Likes like=new Likes(idUser,idNota);
		likes.add(like);
		n.setLikes(likes);
		CollectionReference ref=db.getFirebase().collection("Nota");
		ref.document(String.valueOf(n.getIdnota())).set(n);
	}
	
	@PostMapping("/dislikeNota/{idNota}/{idUser}/")
	public void dislike(@PathVariable int idNota,@PathVariable int idUser) throws InterruptedException, ExecutionException {
		Nota n=getNota(idNota);
		List<Likes>likes=n.getLikes();
		List<Likes>likesDef=new ArrayList<>();
		Likes like=new Likes(idUser,idNota);
		for(Likes l:likes) {
			if(l.getIdUser()!=like.getIdUser() && l.getIdLiked()!=like.getIdLiked()) {
				likesDef.add(l);
			}
		}
		
		n.setLikes(likesDef);
		CollectionReference ref=db.getFirebase().collection("Nota");
		ref.document(String.valueOf(n.getIdnota())).set(n);
		System.out.println("DISLIKE= "+n.toString());
	}
	
	@PostMapping("borrarNota/{idNota}/")
	public void borrarNota(@PathVariable int idNota) throws InterruptedException, ExecutionException {
		CollectionReference ref=db.getFirebase().collection("Nota");
		ref.document(String.valueOf(idNota)).delete();
	}
	
	
	
	
}	
	