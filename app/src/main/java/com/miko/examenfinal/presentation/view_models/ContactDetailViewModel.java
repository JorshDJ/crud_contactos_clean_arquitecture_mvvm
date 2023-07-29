package com.miko.examenfinal.presentation.view_models;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.miko.examenfinal.domain.entities.Contacto;
import com.miko.examenfinal.domain.usecases.DeleteContactUseCase;
import com.miko.examenfinal.domain.usecases.GetContactUseCase;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class ContactDetailViewModel extends ViewModel {

    private final GetContactUseCase getContactUseCase;
    private final DeleteContactUseCase deleteContactUseCase;  // Nuevo
    private LiveData<Contacto> contact;

    @Inject
    public ContactDetailViewModel(GetContactUseCase getContactUseCase, DeleteContactUseCase deleteContactUseCase) {  // Modificado
        this.getContactUseCase = getContactUseCase;
        this.deleteContactUseCase = deleteContactUseCase;  // Nuevo
    }

    public LiveData<Contacto> getContact() {
        return contact;
    }

    public void loadContact(String id) {
        contact = getContactUseCase.execute(id);
    }

    public void deleteContact(String id) {
        deleteContactUseCase.execute(id);
    }
}
