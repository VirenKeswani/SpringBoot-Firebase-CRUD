package com.register.mentee;

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
public class MENTEEServices {
    Firestore dbFirestore = FirestoreClient.getFirestore();

    public String createMENTEE(mentee mentee, String phone) throws InterruptedException, ExecutionException {
        System.out.println(
                "====================================\n" + UUID.randomUUID()
                        + "\n====================================");
        ApiFuture<WriteResult> collectionsApiFuture = dbFirestore.collection("mentee_user").document(phone)
                .set(mentee);
        return collectionsApiFuture.get().getUpdateTime().toString();
    }

    public mentee getMENTEE(String phone) throws InterruptedException, ExecutionException {
        DocumentReference documentReference = dbFirestore.collection("mentee_user").document(phone);
        ApiFuture<DocumentSnapshot> future = documentReference.get();
        DocumentSnapshot document = future.get();
        mentee mentee;
        if (document.exists()) {
            mentee = document.toObject(mentee.class);
            return mentee;
        } else {
            return null;
        }

    }

    public String updateMENTEE(mentee mentee, String phone) throws InterruptedException, ExecutionException {
        ApiFuture<WriteResult> collectionApiFuture = dbFirestore.collection("mentee_user").document(phone)
                .set(mentee);
        return collectionApiFuture.get().getUpdateTime().toString();

    }

    public String deleteMENTEE(String phone) {
        ApiFuture<WriteResult> writeResult = dbFirestore.collection("mentee_user").document(phone).delete();
        return "Document with ID " + phone + " has been deleted";
    }

}
