package com.lounah.messenger.domain.interactor;

import android.support.annotation.NonNull;

import com.lounah.messenger.data.Api;
import com.lounah.messenger.domain.BaseUseCase;
import com.lounah.messenger.presentation.model.ServerResponse;

import javax.inject.Inject;

import io.reactivex.Observable;

public final class Authorize extends BaseUseCase<ServerResponse, Authorize.Params>{

    private final Api api;

    @Inject
    public Authorize(@NonNull final Api api) {
        this.api = api;
    }

    @Override
    protected Observable<ServerResponse> buildUseCaseObservable(Authorize.Params params) {
        return api.login(params.username, params.password);
    }

    public static final class Params {
        private final String username;
        private final String password;

        private Params(@NonNull final String username, @NonNull final String password) {
            this.username = username;
            this.password = password;
        }

        public static Params forLogin(@NonNull final String username, @NonNull final String password) {
            return new Params(username, password);
        }

    }


}
