package com.miko.examenfinal.di;


import com.miko.examenfinal.data.datasource.FirebaseDataSource;
import com.miko.examenfinal.data.repositories.ContactRepositoryImpl;
import com.miko.examenfinal.domain.repositories.ContactRepository;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
public class AppModule {

    @Provides
    public FirebaseDataSource provideFirebaseDataSource() {
        return new FirebaseDataSource();
    }

    @Provides
    public ContactRepository provideContactRepository(FirebaseDataSource firebaseDataSource) {
        return new ContactRepositoryImpl(firebaseDataSource);
    }

}
