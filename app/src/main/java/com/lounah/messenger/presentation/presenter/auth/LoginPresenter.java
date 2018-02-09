package com.lounah.messenger.presentation.presenter.auth;

import android.support.annotation.NonNull;

import com.lounah.messenger.domain.interactor.Authorize;
import com.lounah.messenger.presentation.model.ServerResponse;
import com.lounah.messenger.presentation.presenter.BasePresenter;
import com.lounah.messenger.presentation.view.views.LoginView;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.observers.DisposableObserver;

@Singleton
public class LoginPresenter extends BasePresenter<LoginView> {

    private LoginView mView;
    private final Authorize usecase;

    @Inject
    public LoginPresenter(@NonNull final Authorize usecase) {
        this.usecase = usecase;
    }

    public void login(@NonNull final String username,
                      @NonNull final String password) {
        mView.onShowLoadingView();
        usecase.execute(new LoginDisposable(),
                Authorize.Params.forLogin(username, password));
    }

    @Override
    public void setView(@NonNull LoginView view) {
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
        usecase.dispose();
        mView = null;
    }

    private final class LoginDisposable extends DisposableObserver<ServerResponse> {

        @Override
        public void onNext(ServerResponse serverResponse) {
            if (serverResponse.getCode() == 200) {
                LoginPresenter.this.mView.onStartMainActivity();
            }
        }

        @Override
        public void onError(Throwable e) {
            LoginPresenter.this.mView.onHideLoadingView();
            LoginPresenter.this.mView.onShowLoginError();
        }

        @Override
        public void onComplete() {
            LoginPresenter.this.mView.onHideLoadingView();
        }
    }

}
