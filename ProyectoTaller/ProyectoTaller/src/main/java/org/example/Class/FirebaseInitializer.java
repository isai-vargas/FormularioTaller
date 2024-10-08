package org.example.Class;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.cloud.FirestoreClient;

import java.io.FileInputStream;
import java.io.IOException;

public class FirebaseInitializer {
    public static void initializeFirebase() throws IOException {
        // Cargar el archivo de credenciales
        FileInputStream serviceAccount = new FileInputStream("src/main/resources/formulariotaller-d9acf-firebase-adminsdk-nm2pd-ef9a56c1b5.json");

        // Configurar Firebase con las credenciales y opciones necesarias
        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .setStorageBucket("colegio-99987.appspot.com") // Cambia este nombre según tu bucket de Firebase
                .build();

        // Inicializar Firebase
        if (FirebaseApp.getApps().isEmpty()) {
            FirebaseApp.initializeApp(options);
        }

        System.out.println("Firebase inicializado correctamente.");
    }

    public static Firestore getFirestore() {
        return FirestoreClient.getFirestore();
    }


}
