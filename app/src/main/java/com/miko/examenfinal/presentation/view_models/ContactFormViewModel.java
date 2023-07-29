package com.miko.examenfinal.presentation.view_models;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.miko.examenfinal.domain.entities.Contacto;
import com.miko.examenfinal.domain.usecases.CreateContactUseCase;
import com.miko.examenfinal.domain.usecases.GetContactUseCase;
import com.miko.examenfinal.domain.usecases.UpdateContactUseCase;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class ContactFormViewModel extends ViewModel {

    private final CreateContactUseCase createContactUseCase;
    private final UpdateContactUseCase updateContactUseCase;
    private final GetContactUseCase getContactUseCase;  // Añadido

    private LiveData<Contacto> contact;

    @Inject
    public ContactFormViewModel(CreateContactUseCase createContactUseCase, UpdateContactUseCase updateContactUseCase, GetContactUseCase getContactUseCase) {  // Modificado
        this.createContactUseCase = createContactUseCase;
        this.updateContactUseCase = updateContactUseCase;
        this.getContactUseCase = getContactUseCase;  // Añadido
    }

    public void createContact(Contacto contacto) {
        createContactUseCase.execute(contacto);
    }

    public void updateContact(Contacto contacto) {
        updateContactUseCase.execute(contacto);
    }

    public void loadContact(String id) {
        contact = getContactUseCase.execute(id);
    }

    public LiveData<Contacto> getContact() {
        return contact;
    }
}
