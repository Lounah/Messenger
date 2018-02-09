package com.lounah.messenger.presentation.view.views;

import android.support.annotation.NonNull;

public interface MainView extends NetworkView {

    void setToolbarTitle(@NonNull final String title);

    void setToolbarSubtitle(@NonNull final String subTitle);

    void onUpdateToolbar();

    void onShowError(@NonNull final Throwable error);

}
