package com.miko.examenfinal.presentation.view_models;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.miko.examenfinal.domain.entities.Contacto;
import com.miko.examenfinal.domain.usecases.GetAllContactsUseCase;


import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel

public class ContactListViewModel extends ViewModel {

    private final GetAllContactsUseCase getAllContactsUseCase;
    private final LiveData<List<Contacto>> contacts;

    @Inject
    public ContactListViewModel(GetAllContactsUseCase getAllContactsUseCase) {
        this.getAllContactsUseCase = getAllContactsUseCase;
        this.contacts = getAllContactsUseCase.execute();
    }

    public LiveData<List<Contacto>> getContacts() {
        return contacts;
    }
}