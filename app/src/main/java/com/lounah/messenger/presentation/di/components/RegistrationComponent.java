package com.lounah.messenger.presentation.di.components;


import com.lounah.messenger.presentation.di.modules.FirebaseApiModule;
import com.lounah.messenger.presentation.di.modules.RegistrationModule;
import com.lounah.messenger.presentation.di.modules.RegistrationPresenterModule;
import com.lounah.messenger.presentation.view.fragments.auth.RegistrationFragment;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {FirebaseApiModule.class, RegistrationModule.class, RegistrationPresenterModule.class})
public interface RegistrationComponent {
    void inject(RegistrationFragment fragment);
}
