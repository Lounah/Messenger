package com.lounah.messenger.presentation.view.views;

import android.support.annotation.NonNull;

public interface RegistrationView extends NetworkView {

    void onShowRegistrationError();

    void onStartMainActivity();

    void onRegister(@NonNull final String username,
                    @NonNull final String password,
                    @NonNull final String rePassword);

}
