package com.miko.examenfinal.domain.usecases;

import androidx.lifecycle.LiveData;

import com.miko.examenfinal.domain.entities.Contacto;
import com.miko.examenfinal.domain.repositories.ContactRepository;

import java.util.List;

/*
//No
public class GetAllContactsUseCase {

    private final ContactRepository contactRepository;

    public GetAllContactsUseCase(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    public List<Contacto> execute() {
        return contactRepository.getAllContacts();
    }
}

 */

public class GetAllContactsUseCase {

    private final ContactRepository contactRepository;

    public GetAllContactsUseCase(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    public LiveData<List<Contacto>> execute() {
        return contactRepository.getAllContacts();
    }
}