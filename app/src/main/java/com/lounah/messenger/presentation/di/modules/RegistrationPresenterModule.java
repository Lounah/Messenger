package com.lounah.messenger.presentation.di.modules;

import android.support.annotation.NonNull;

import com.lounah.messenger.data.Api;
import com.lounah.messenger.domain.interactor.CreateAccount;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;


@Module
public class RegistrationPresenterModule {

    @Provides
    @Singleton
    CreateAccount provideUseCase(@NonNull final Api api) {
        return new CreateAccount(api);
    }

}
