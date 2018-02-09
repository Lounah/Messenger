package com.lounah.messenger.presentation.di.modules;


import android.support.annotation.NonNull;

import com.lounah.messenger.domain.interactor.Authorize;
import com.lounah.messenger.presentation.presenter.auth.LoginPresenter;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class LoginModule {

    @Provides
    @Singleton
    LoginPresenter loginPresenter(@NonNull final Authorize useCase) {
        return new LoginPresenter(useCase);
    }
}
