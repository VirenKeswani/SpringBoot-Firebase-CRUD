package com.example.mentor;

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
public class MENTORService {

    Firestore dbFirestore = FirestoreClient.getFirestore();

    public String createMENTOR(mentor mentor, String phone) throws InterruptedException, ExecutionException {
        System.out.println(
                "====================================\n" + UUID.randomUUID()
                        + "\n====================================");
        ApiFuture<WriteResult> collectionsApiFuture = dbFirestore.collection("MENTOR_user").document(phone)
                .set(mentor);
        return collectionsApiFuture.get().getUpdateTime().toString();
    }

    public mentor getMENTOR(String phone) throws InterruptedException, ExecutionException {
        DocumentReference documentReference = dbFirestore.collection("MENTOR_user").document(phone);
        ApiFuture<DocumentSnapshot> future = documentReference.get();
        DocumentSnapshot document = future.get();
        mentor mentor;
        if (document.exists()) {
            mentor = document.toObject(mentor.class);
            return mentor;
        } else {
            return null;
        }

    }

    public String updateMENTOR(mentor mentor, String phone) throws InterruptedException, ExecutionException {
        ApiFuture<WriteResult> collectionApiFuture = dbFirestore.collection("MENTOR_user").document(phone)
                .set(mentor);
        return collectionApiFuture.get().getUpdateTime().toString();

    }

    public String deleteMENTOR(String phone) {
        ApiFuture<WriteResult> writeResult = dbFirestore.collection("MENTOR_user").document(phone).delete();
        return "Document with ID " + phone + " has been deleted";
    }

}
