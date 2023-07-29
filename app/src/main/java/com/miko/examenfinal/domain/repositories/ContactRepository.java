package com.miko.examenfinal.domain.repositories;

import androidx.lifecycle.LiveData;

import com.miko.examenfinal.domain.entities.Contacto;

import java.util.List;

public interface ContactRepository {
    /*
    List<Contacto> getAllContacts();

     */
    LiveData<List<Contacto>> getAllContacts();

    //Contacto getContact(String id); //Sin live DATA
    LiveData<Contacto> getContact(String id);//con live data

    void createContact(Contacto contacto);

    void updateContact(Contacto contacto);

    void deleteContact(String id);
}