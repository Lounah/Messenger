package com.lounah.messenger.presentation.presenter.auth;

import android.support.annotation.NonNull;

import com.lounah.messenger.domain.interactor.CreateAccount;
import com.lounah.messenger.presentation.model.ServerResponse;
import com.lounah.messenger.presentation.presenter.BasePresenter;
import com.lounah.messenger.presentation.view.views.RegistrationView;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.observers.DisposableObserver;

@Singleton
public class RegistrationPresenter extends BasePresenter<RegistrationView> {

    private RegistrationView mView;

    private final CreateAccount usecase;

    @Inject
    public RegistrationPresenter(CreateAccount usecase) {
        this.usecase = usecase;
    }

    public void createAccount(@NonNull final String username, @NonNull final String password) {
        mView.onShowLoadingView();
        usecase.execute(new RegistrationObserver(), CreateAccount.Params.forNewAccount(username, password));
    }

    @Override
    public void setView(@NonNull RegistrationView view) {
        mView = view;
    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void destroy() {
        mView = null;
        usecase.dispose();
    }

    private final class RegistrationObserver extends DisposableObserver<ServerResponse> {

        @Override
        public void onNext(ServerResponse serverResponse) {
            if (serverResponse.getCode() == 200)
            RegistrationPresenter.this.mView.onStartMainActivity();
        }

        @Override
        public void onError(Throwable e) {
            RegistrationPresenter.this.mView.onHideLoadingView();
            RegistrationPresenter.this.mView.onShowRegistrationError();
        }

        @Override
        public void onComplete() {
            RegistrationPresenter.this.mView.onHideLoadingView();
        }
    }
}
