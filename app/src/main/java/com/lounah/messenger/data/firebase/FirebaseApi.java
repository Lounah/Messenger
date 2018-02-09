package com.lounah.messenger.data.firebase;

import android.support.annotation.NonNull;

import com.lounah.messenger.data.Api;
import com.lounah.messenger.exception.AuthenticationException;
import com.lounah.messenger.presentation.model.ServerResponse;

import javax.inject.Inject;

import io.reactivex.Observable;

public class FirebaseApi implements Api {

    @Inject
    public FirebaseApi() {}

    @Override
    public Observable<ServerResponse> login(@NonNull String username, @NonNull String password) {
        return Observable.create(emitter -> {
            Thread.sleep(4000);
            emitter.onNext(ServerResponse.withCode(200));
        });
    }

    @Override
    public Observable<ServerResponse> createAccount(@NonNull String username, @NonNull String password) {
        return Observable.create(emitter -> {
            Thread.sleep(4000);
            emitter.onNext(ServerResponse.withCode(200));
        });
    }
}
