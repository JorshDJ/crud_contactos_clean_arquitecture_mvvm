package com.miko.examenfinal.data.datasource;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.android.gms.tasks.Tasks;
import com.google.firebase.firestore.FirebaseFirestore;
import com.miko.examenfinal.domain.entities.Contacto;

import java.util.ArrayList;
import java.util.List;

public class FirebaseDataSource {

    private FirebaseFirestore db;

    public FirebaseDataSource() {
        db = FirebaseFirestore.getInstance();
    }

    /*

    Sin live data
    public List<Contacto> getAllContacts() {
        try {
            return Tasks.await(db.collection("contactos").get()).toObjects(Contacto.class);
        } catch (Exception e) {
            // Manejo de errores
            return new ArrayList<>();
        }
    }

     */
    public LiveData<List<Contacto>> getAllContacts() {
        MutableLiveData<List<Contacto>> contactList = new MutableLiveData<>();
        db.collection("contactos").get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                contactList.setValue(task.getResult().toObjects(Contacto.class));
            } else {
                // manejo de errores
            }
        });
        return contactList;
    }

    //Sin live data
    /*
    public Contacto getContact(String id) {
        try {
            return Tasks.await(db.collection("contactos").document(id).get()).toObject(Contacto.class);
        } catch (Exception e) {
            // Manejo de errores
            return null;
        }
    }

     */
    //Con live data , para que devuelva LiveData<Contacto>
    public LiveData<Contacto> getContact(String id) {
        MutableLiveData<Contacto> liveDataContacto = new MutableLiveData<>();
        db.collection("contactos").document(id).get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                liveDataContacto.setValue(task.getResult().toObject(Contacto.class));
            } else {
                // manejo de errores
            }
        });
        return liveDataContacto;
    }


    public void createContact(Contacto contacto) {
        try {
            // Genera un ID único para el nuevo contacto
            String id = db.collection("contactos").document().getId();
            contacto.setId(id);
            Tasks.await(db.collection("contactos").document(contacto.getId()).set(contacto));
        } catch (Exception e) {
            // Manejo de errores
        }
    }

    public void updateContact(Contacto contacto) {
        try {
            Tasks.await(db.collection("contactos").document(contacto.getId()).set(contacto));
        } catch (Exception e) {
            // Manejo de errores
        }
    }

    public void deleteContact(String id) {
        try {
            Tasks.await(db.collection("contactos").document(id).delete());
        } catch (Exception e) {
            // Manejo de errores
        }
    }
}