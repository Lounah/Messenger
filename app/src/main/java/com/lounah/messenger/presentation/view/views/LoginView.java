package com.lounah.messenger.presentation.view.views;

import android.support.annotation.NonNull;

public interface LoginView extends NetworkView {

    void onShowLoginError();

    void onStartMainActivity();

    void onLogin(@NonNull final String username, @NonNull final String password);

}
