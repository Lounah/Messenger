package com.lounah.messenger.domain.interactor;

import android.support.annotation.NonNull;

import com.lounah.messenger.data.Api;
import com.lounah.messenger.domain.BaseUseCase;
import com.lounah.messenger.presentation.model.ServerResponse;

import javax.inject.Inject;

import io.reactivex.Observable;

public class CreateAccount extends BaseUseCase<ServerResponse, CreateAccount.Params> {


    private final Api api;

    @Inject
    public CreateAccount(@NonNull final Api api) {
        this.api = api;
    }

    @Override
    protected Observable<ServerResponse> buildUseCaseObservable(CreateAccount.Params params) {
        return api.createAccount(params.username, params.password);
    }

    public static final class Params {
        private final String username;
        private final String password;

        private Params(@NonNull final String username, @NonNull final String password) {
            this.username = username;
            this.password = password;
        }

        public static CreateAccount.Params forNewAccount(@NonNull final String username, @NonNull final String password) {
            return new CreateAccount.Params(username, password);
        }

    }

}
