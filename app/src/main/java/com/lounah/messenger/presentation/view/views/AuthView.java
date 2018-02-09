package com.lounah.messenger.presentation.view.views;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;

public interface AuthView extends NetworkView {

    void onSetDefaultFragment();

    void onReplaceFragmentWithBackStack(@NonNull final Fragment fragment);

    void onReplaceFragmentWithoutBackStack(@NonNull final Fragment fragment);

    void onUpdateToolbar();

    void onSetToolbarTitle(@NonNull final String title);

    void onStartMainActivity();

}
