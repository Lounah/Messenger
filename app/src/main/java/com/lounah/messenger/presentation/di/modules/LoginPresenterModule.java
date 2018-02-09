package com.lounah.messenger.presentation.di.modules;


import android.support.annotation.NonNull;

import com.lounah.messenger.data.Api;
import com.lounah.messenger.data.firebase.FirebaseApi;
import com.lounah.messenger.domain.interactor.Authorize;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class LoginPresenterModule {

    @Provides
    @Singleton
    Authorize provideUseCase(@NonNull final Api api) {
        return new Authorize(api);
    }

}
