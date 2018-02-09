package com.lounah.messenger.presentation.presenter;

import android.support.annotation.NonNull;

public abstract class BasePresenter<V> {

    public abstract void setView(@NonNull final V view);

    public abstract void resume();

    public abstract void pause();

    public abstract void destroy();

}
