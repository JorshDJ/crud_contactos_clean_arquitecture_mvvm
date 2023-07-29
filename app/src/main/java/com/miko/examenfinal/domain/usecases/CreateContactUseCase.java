package com.miko.examenfinal.domain.usecases;

import com.miko.examenfinal.domain.entities.Contacto;
import com.miko.examenfinal.domain.repositories.ContactRepository;

public class CreateContactUseCase {

    private final ContactRepository contactRepository;

    public CreateContactUseCase(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    public void execute(Contacto contacto) {
        contactRepository.createContact(contacto);
    }
}