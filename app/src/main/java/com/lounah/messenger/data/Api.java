package com.lounah.messenger.data;

import android.support.annotation.NonNull;

import com.lounah.messenger.presentation.model.ServerResponse;

import io.reactivex.Observable;

public interface Api {

    Observable<ServerResponse> login(@NonNull final String username, @NonNull final String password);

    Observable<ServerResponse> createAccount(@NonNull final String username, @NonNull final String password);


}
