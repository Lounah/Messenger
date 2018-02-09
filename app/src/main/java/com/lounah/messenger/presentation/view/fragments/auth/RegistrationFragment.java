package com.lounah.messenger.presentation.view.fragments.auth;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.jakewharton.rxbinding2.widget.RxTextView;
import com.lounah.messenger.R;
import com.lounah.messenger.presentation.di.MessengerApplication;
import com.lounah.messenger.presentation.di.scopes.ActivityScoped;
import com.lounah.messenger.presentation.presenter.auth.RegistrationPresenter;
import com.lounah.messenger.presentation.view.activities.AuthActivity;
import com.lounah.messenger.presentation.view.fragments.BaseFragment;
import com.lounah.messenger.presentation.view.views.RegistrationView;
import com.lounah.messenger.utils.CredentialsReviewer;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

@ActivityScoped
public class RegistrationFragment extends BaseFragment implements RegistrationView {

    @BindView(R.id.login_et)
    EditText loginEt;

    @BindView(R.id.password_et)
    EditText passwordEt;

    @BindView(R.id.password_re_enter_et)
    EditText rePasswordEt;

    @BindView(R.id.signup_button)
    Button signUpBtn;

    private Disposable inputObserver;

    @Inject
    RegistrationPresenter presenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(false);
        MessengerApplication.getRegistrationComponent().inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_signup, container, false);
        setToolbarTitle();
        ((AuthActivity) getActivity()).onUpdateToolbar();
        return view;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        presenter.setView(this);
        onListenInput();
    }

    @Override
    protected void setToolbarTitle() {
        ((AuthActivity) getActivity()).onSetToolbarTitle(getResources().getString(R.string.signup));
    }

    @Override
    public boolean isNested() {
        return true;
    }

    @Override
    protected void onMenuItemClicked() {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        inputObserver.dispose();
        presenter.destroy();
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.setView(this);
    }

    private void onListenInput() {
        inputObserver = Observable.combineLatest(
                RxTextView.textChanges(loginEt),
                RxTextView.textChanges(passwordEt),
                RxTextView.textChanges(rePasswordEt),
                (login, password, rePassword) ->
                        CredentialsReviewer.review(login.toString(),
                                password.toString(),
                                rePassword.toString())
        ).subscribe(signUpBtn::setEnabled);
    }

    @Override
    public void onShowLoadingView() {
        ((AuthActivity) getActivity()).onShowLoadingView();
    }

    @Override
    public void onHideLoadingView() {
        ((AuthActivity) getActivity()).onHideLoadingView();
    }

    @Override
    public void onShowNetworkError(@NonNull String errorMsg) {
        Toast.makeText(getContext(), errorMsg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onShowRegistrationError() {
        Toast.makeText(getContext(), R.string.registration_error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onStartMainActivity() {
        ((AuthActivity) getActivity()).onStartMainActivity();
    }

    @OnClick(R.id.signup_button)
    public void onCreateAccountButtonClicked() {
        final String username = loginEt.getText().toString();
        final String password = passwordEt.getText().toString();
        final String rePass = rePasswordEt.getText().toString();
        onRegister(username, password, rePass);
    }

    @Override
    public void onRegister(@NonNull String username, @NonNull String password, @NonNull String rePassword) {
        presenter.createAccount(username, password);
    }
}
