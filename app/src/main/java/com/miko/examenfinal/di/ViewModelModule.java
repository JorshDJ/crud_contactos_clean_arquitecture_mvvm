package com.miko.examenfinal.di;

import com.miko.examenfinal.domain.repositories.ContactRepository;
import com.miko.examenfinal.domain.usecases.CreateContactUseCase;
import com.miko.examenfinal.domain.usecases.DeleteContactUseCase;
import com.miko.examenfinal.domain.usecases.GetAllContactsUseCase;
import com.miko.examenfinal.domain.usecases.GetContactUseCase;
import com.miko.examenfinal.domain.usecases.UpdateContactUseCase;
import com.miko.examenfinal.presentation.view_models.ContactDetailViewModel;
import com.miko.examenfinal.presentation.view_models.ContactFormViewModel;
import com.miko.examenfinal.presentation.view_models.ContactListViewModel;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ActivityRetainedComponent;

@Module
@InstallIn(ActivityRetainedComponent.class)
public class ViewModelModule {
    /*
    @Provides
    public ContactListViewModel provideContactListViewModel(ContactRepository contactRepository) {
        GetAllContactsUseCase getAllContactsUseCase = new GetAllContactsUseCase(contactRepository);
        return new ContactListViewModel(getAllContactsUseCase);
    }

     */
    @Provides
    public GetAllContactsUseCase provideGetAllContactsUseCase(ContactRepository contactRepository) {
        return new GetAllContactsUseCase(contactRepository);
    }

    /*
    @Provides
    public ContactDetailViewModel provideContactDetailViewModel(ContactRepository contactRepository) {
        GetContactUseCase getContactUseCase = new GetContactUseCase(contactRepository);
        return new ContactDetailViewModel(getContactUseCase);
    }

    @Provides
    public ContactFormViewModel provideContactFormViewModel(ContactRepository contactRepository) {
        CreateContactUseCase createContactUseCase = new CreateContactUseCase(contactRepository);
        UpdateContactUseCase updateContactUseCase = new UpdateContactUseCase(contactRepository);
        return new ContactFormViewModel(createContactUseCase, updateContactUseCase);
    }

     */

    /*
    @Provides
    public GetAllContactsUseCase provideGetAllContactsUseCase(ContactRepository contactRepository) {
        return new GetAllContactsUseCase(contactRepository);
    }

     */

    @Provides
    public GetContactUseCase provideGetContactUseCase(ContactRepository contactRepository) {
        return new GetContactUseCase(contactRepository);
    }

    @Provides
    public CreateContactUseCase provideCreateContactUseCase(ContactRepository contactRepository) {
        return new CreateContactUseCase(contactRepository);
    }

    @Provides
    public UpdateContactUseCase provideUpdateContactUseCase(ContactRepository contactRepository) {
        return new UpdateContactUseCase(contactRepository);
    }
    @Provides
    public DeleteContactUseCase provideDeleteContactUseCase(ContactRepository contactRepository) {
        return new DeleteContactUseCase(contactRepository);
    }
}
