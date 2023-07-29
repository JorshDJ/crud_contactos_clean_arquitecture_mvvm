package com.miko.examenfinal.domain.usecases;

import androidx.lifecycle.LiveData;

import com.miko.examenfinal.domain.entities.Contacto;
import com.miko.examenfinal.domain.repositories.ContactRepository;

/*
public class GetContactUseCase {

    private final ContactRepository contactRepository;

    public GetContactUseCase(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    public Contacto execute(String id) {
        return contactRepository.getContact(id);
    }
}

 */
public class GetContactUseCase {

    private final ContactRepository contactRepository;

    public GetContactUseCase(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    public LiveData<Contacto> execute(String id) {
        return contactRepository.getContact(id);
    }
}