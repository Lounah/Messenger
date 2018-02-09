package com.lounah.messenger.presentation.di;

import android.app.Application;

import com.lounah.messenger.presentation.di.components.DaggerLoginComponent;
import com.lounah.messenger.presentation.di.components.DaggerProfileComponent;
import com.lounah.messenger.presentation.di.components.DaggerRegistrationComponent;
import com.lounah.messenger.presentation.di.components.LoginComponent;
import com.lounah.messenger.presentation.di.components.ProfileComponent;
import com.lounah.messenger.presentation.di.components.RegistrationComponent;
import com.lounah.messenger.presentation.di.modules.FirebaseApiModule;
import com.lounah.messenger.presentation.di.modules.LoginModule;
import com.lounah.messenger.presentation.di.modules.LoginPresenterModule;
import com.lounah.messenger.presentation.di.modules.ProfileModule;
import com.lounah.messenger.presentation.di.modules.ProfilePresenterModule;
import com.lounah.messenger.presentation.di.modules.RegistrationModule;
import com.lounah.messenger.presentation.di.modules.RegistrationPresenterModule;
import com.squareup.leakcanary.LeakCanary;

public class MessengerApplication extends Application {

    private static LoginComponent loginComponent;
    private static RegistrationComponent registrationComponent;
    private static ProfileComponent profileComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        installLeakCanary();
        buildLoginComponent();
        buildRegistrationComponent();
        buildProfileComponent();
    }

    private void installLeakCanary() {
        if (LeakCanary.isInAnalyzerProcess(this)) {
            return;
        }
        LeakCanary.install(this);
    }

    private void buildLoginComponent() {
        loginComponent = DaggerLoginComponent.builder()
                .loginModule(new LoginModule())
                .loginPresenterModule(new LoginPresenterModule())
                .firebaseApiModule(new FirebaseApiModule())
                .build();
    }

    private void buildRegistrationComponent() {
        registrationComponent = DaggerRegistrationComponent.builder()
                .registrationModule(new RegistrationModule())
                .firebaseApiModule(new FirebaseApiModule())
                .registrationPresenterModule(new RegistrationPresenterModule())
                .build();
    }

    private void buildProfileComponent() {
        profileComponent = DaggerProfileComponent.builder()
                .profileModule(new ProfileModule())
                .profilePresenterModule(new ProfilePresenterModule())
                .build();
    }

    public static LoginComponent getLoginComponent() {
        return loginComponent;
    }

    public static RegistrationComponent getRegistrationComponent() {
        return registrationComponent;
    }

    public static ProfileComponent getProfileComponent() { return profileComponent; }

}
