package org.example.Services;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import org.example.Class.FirebaseInitializer;
import org.example.Models.OrdenTrabajo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class OrdenTrabajoService {

    private static final String COLLECTION_NAME = "ordenesTrabajo";
    private Firestore firestore = FirebaseInitializer.getFirestore();

    // Método para crear o actualizar una orden de trabajo
    public String saveOrdenTrabajo(OrdenTrabajo ordenTrabajo) throws ExecutionException, InterruptedException {
        DocumentReference documentReference;
        if (ordenTrabajo.getId() != null) {
            // Actualizar
            documentReference = firestore.collection(COLLECTION_NAME).document(ordenTrabajo.getId());
        } else {
            // Crear nueva
            documentReference = firestore.collection(COLLECTION_NAME).document();
            ordenTrabajo.setId(documentReference.getId());
        }
        ApiFuture<WriteResult> writeResult = documentReference.set(ordenTrabajo);
        return writeResult.get().getUpdateTime().toString();
    }

    // Método para leer una orden de trabajo por ID
    public OrdenTrabajo getOrdenTrabajoById(String id) throws ExecutionException, InterruptedException {
        DocumentReference documentReference = firestore.collection(COLLECTION_NAME).document(id);
        ApiFuture<DocumentSnapshot> future = documentReference.get();
        DocumentSnapshot document = future.get();
        if (document.exists()) {
            return document.toObject(OrdenTrabajo.class);
        } else {
            return null;
        }
    }

    // Método para obtener todas las ordenes de trabajo
    public List<QueryDocumentSnapshot> getAllOrdenesTrabajo() throws ExecutionException, InterruptedException {
        ApiFuture<QuerySnapshot> future = firestore.collection(COLLECTION_NAME).get();
        return future.get().getDocuments();
    }

    // Método para eliminar una orden de trabajo
    public String deleteOrdenTrabajo(String id) throws ExecutionException, InterruptedException {
        DocumentReference documentReference = firestore.collection(COLLECTION_NAME).document(id);
        ApiFuture<WriteResult> writeResult = documentReference.delete();
        return writeResult.get().getUpdateTime().toString();
    }
}