package com.lounah.messenger.presentation.di.modules;


import android.support.annotation.NonNull;

import com.lounah.messenger.domain.interactor.CreateAccount;
import com.lounah.messenger.presentation.presenter.auth.RegistrationPresenter;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class RegistrationModule {

    @Provides
    @Singleton
    RegistrationPresenter providePresenter(@NonNull final CreateAccount usecase) {
        return new RegistrationPresenter(usecase);
    }

}
