package com.example.demo.servicios;

import java.io.FileInputStream;
import java.io.IOException;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;

@Service
public class FirebaseInitializer {
	@PostConstruct
	private void initDB() throws IOException {
		FileInputStream serviceAccount =new FileInputStream("C:\\Users\\begoe\\Documents\\workspace-spring-tool-suite-4-4.20.0.RELEASE\\SocialPics\\src\\main\\resources\\socialpics-7014e-firebase-adminsdk-szj70-cced1299ea.json");
		FirebaseOptions options = new FirebaseOptions.Builder()
				  .setCredentials(GoogleCredentials.fromStream(serviceAccount))
				  .build();
		if(FirebaseApp.getApps().isEmpty()) {
			FirebaseApp.initializeApp(options);
		}
	}
	
	public Firestore getFirebase() {
		return FirestoreClient.getFirestore();
	}
}
