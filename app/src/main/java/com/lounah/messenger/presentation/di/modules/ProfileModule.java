package com.lounah.messenger.presentation.di.modules;

import com.lounah.messenger.presentation.presenter.profile.ProfilePresenter;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ProfileModule {

    @Provides
    @Singleton
    ProfilePresenter providePresenter() {
        return new ProfilePresenter();
    }

}
