package com.lounah.messenger.presentation.view.views;

import android.support.annotation.NonNull;

public interface NetworkView extends BaseView {

    void onShowLoadingView();

    void onHideLoadingView();

    void onShowNetworkError(@NonNull final String errorMsg);

}
