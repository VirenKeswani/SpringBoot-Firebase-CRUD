package com.example.mentee;

import java.util.concurrent.ExecutionException;
import java.util.UUID;
import org.springframework.stereotype.Service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;

@Service
public class CRUDService {

    public String createCRUD(crud crud, String phone) throws InterruptedException, ExecutionException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        System.out.println(
                "====================================\n" + UUID.randomUUID()
                        + "\n====================================");
        ApiFuture<WriteResult> collectionsApiFuture = dbFirestore.collection("crud_user").document(phone)
                .set(crud);
        return collectionsApiFuture.get().getUpdateTime().toString();
    }

    public crud getCRUD(String phone) throws InterruptedException, ExecutionException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        DocumentReference documentReference = dbFirestore.collection("crud_user").document(phone);
        ApiFuture<DocumentSnapshot> future = documentReference.get();
        DocumentSnapshot document = future.get();
        crud crud;
        if (document.exists()) {
            crud = document.toObject(crud.class);
            return crud;
        } else {
            return null;
        }

    }

    public String updateCRUD(crud crud, String phone) throws InterruptedException, ExecutionException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionApiFuture = dbFirestore.collection("crud_user").document(phone)
                .set(crud);
        return collectionApiFuture.get().getUpdateTime().toString();

    }

    public String deleteCRUD(String phone) {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> writeResult = dbFirestore.collection("crud_user").document(phone).delete();
        return "Document with ID " + phone + " has been deleted";
    }
}
