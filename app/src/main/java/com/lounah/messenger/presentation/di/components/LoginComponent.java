package com.lounah.messenger.presentation.di.components;


import com.lounah.messenger.presentation.di.modules.FirebaseApiModule;
import com.lounah.messenger.presentation.di.modules.LoginModule;
import com.lounah.messenger.presentation.di.modules.LoginPresenterModule;
import com.lounah.messenger.presentation.view.fragments.auth.LoginFragment;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {LoginModule.class, LoginPresenterModule.class, FirebaseApiModule.class})
public interface LoginComponent {
    void inject(LoginFragment loginFragment);
}
