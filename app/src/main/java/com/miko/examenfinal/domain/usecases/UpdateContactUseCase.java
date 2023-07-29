package com.miko.examenfinal.domain.usecases;

import com.miko.examenfinal.domain.entities.Contacto;
import com.miko.examenfinal.domain.repositories.ContactRepository;

public class UpdateContactUseCase {

    private final ContactRepository contactRepository;

    public UpdateContactUseCase(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    public void execute(Contacto contacto) {
        contactRepository.updateContact(contacto);
    }
}