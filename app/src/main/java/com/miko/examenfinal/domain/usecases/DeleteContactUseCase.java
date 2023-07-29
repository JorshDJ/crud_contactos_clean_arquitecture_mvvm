package com.miko.examenfinal.domain.usecases;

import com.miko.examenfinal.domain.repositories.ContactRepository;

public class DeleteContactUseCase {

    private final ContactRepository contactRepository;

    public DeleteContactUseCase(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    public void execute(String id) {
        contactRepository.deleteContact(id);
    }
}