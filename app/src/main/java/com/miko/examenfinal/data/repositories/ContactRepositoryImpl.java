package com.miko.examenfinal.data.repositories;

import androidx.lifecycle.LiveData;

import com.miko.examenfinal.data.datasource.FirebaseDataSource;
import com.miko.examenfinal.domain.entities.Contacto;
import com.miko.examenfinal.domain.repositories.ContactRepository;

import java.util.List;

public class ContactRepositoryImpl implements ContactRepository {

    private final FirebaseDataSource firebaseDataSource;

    public ContactRepositoryImpl(FirebaseDataSource firebaseDataSource) {
        this.firebaseDataSource = firebaseDataSource;
    }
    /*
    @Override
    public List<Contacto> getAllContacts() {
        return firebaseDataSource.getAllContacts();
    }

     */

    @Override
    public LiveData<List<Contacto>> getAllContacts() {
        return firebaseDataSource.getAllContacts();
    }

    /*
    //Sin live Data
    @Override
    public Contacto getContact(String id) {
        return firebaseDataSource.getContact(id);
    }

     */
    //Con live data
    @Override
    public LiveData<Contacto> getContact(String id) {
        return firebaseDataSource.getContact(id);
    }


    @Override
    public void createContact(Contacto contacto) {
        firebaseDataSource.createContact(contacto);
    }

    @Override
    public void updateContact(Contacto contacto) {
        firebaseDataSource.updateContact(contacto);
    }

    @Override
    public void deleteContact(String id) {
        firebaseDataSource.deleteContact(id);
    }
}