package com.lounah.messenger.presentation.view.fragments.auth;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.jakewharton.rxbinding2.widget.RxTextView;
import com.lounah.messenger.R;
import com.lounah.messenger.presentation.di.MessengerApplication;
import com.lounah.messenger.presentation.di.scopes.ActivityScoped;
import com.lounah.messenger.presentation.presenter.auth.LoginPresenter;
import com.lounah.messenger.presentation.view.activities.AuthActivity;
import com.lounah.messenger.presentation.view.fragments.BaseFragment;
import com.lounah.messenger.presentation.view.views.LoginView;
import com.lounah.messenger.utils.CredentialsReviewer;


import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

@ActivityScoped
public class LoginFragment extends BaseFragment implements LoginView {

    @BindView(R.id.login_et)
    EditText loginEt;

    @BindView(R.id.password_et)
    EditText passwordEt;

    @BindView(R.id.login_button)
    Button loginBtn;

    @BindView(R.id.signup_tv)
    TextView signUpTv;

    private Disposable inputObserver;

    @Inject
    LoginPresenter presenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(false);
        MessengerApplication.getLoginComponent().inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        setToolbarTitle();
        ((AuthActivity) getActivity()).onUpdateToolbar();
        return view;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        presenter.setView(this);
        onListenUserInput();
    }

    @Override
    protected void setToolbarTitle() {
        ((AuthActivity) getActivity()).onSetToolbarTitle(getResources().getString(R.string.auth));
    }

    @Override
    public boolean isNested() {
        return false;
    }

    @Override
    protected void onMenuItemClicked() {

    }

    @OnClick(R.id.signup_tv)
    public void onSignUpTvClicked() {
       ((AuthActivity) getActivity()).onReplaceFragmentWithBackStack(new RegistrationFragment());
    }

    @OnClick(R.id.login_button)
    public void onLoginButtonClicked() {
        final String username = loginEt.getText().toString();
        final String password = passwordEt.getText().toString();
        onLogin(username, password);
    }

    private void onListenUserInput() {
        inputObserver = Observable.combineLatest(
                RxTextView.textChanges(loginEt),
                RxTextView.textChanges(passwordEt),
                (login, password) -> CredentialsReviewer.review(login.toString(), password.toString())
        ).subscribe(loginBtn::setEnabled);
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
    public void onShowLoginError() {
        Toast.makeText(getContext(), R.string.login_error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onStartMainActivity() {
        ((AuthActivity) getActivity()).onStartMainActivity();
    }

    @Override
    public void onLogin(@NonNull String username, @NonNull String password) {
        presenter.login(username, password);
    }
}
