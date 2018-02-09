package com.lounah.messenger.presentation.di.components;


import com.lounah.messenger.presentation.di.modules.ProfileModule;
import com.lounah.messenger.presentation.di.modules.ProfilePresenterModule;
import com.lounah.messenger.presentation.view.fragments.profile.ProfileFragment;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ProfilePresenterModule.class, ProfileModule.class})
public interface ProfileComponent {
    void inject(ProfileFragment fragment);
}
