package com.lounah.messenger.presentation.view.activities;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.lounah.messenger.R;
import com.lounah.messenger.presentation.view.fragments.auth.LoginFragment;
import com.lounah.messenger.presentation.view.views.AuthView;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.annotations.Nullable;

public class AuthActivity extends AppCompatActivity implements AuthView {

    private static final int FRAGMENT_CONTAINER_ID = R.id.fragmentContainer;

    private FragmentManager mFragmentManager;

    private MaterialDialog loadingView;

    @BindView(R.id.auth_toolbar)
    Toolbar toolbar;

    @Override
    public void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);
        ButterKnife.bind(this);
        mFragmentManager = getSupportFragmentManager();
        setSupportActionBar(toolbar);
        onSetDefaultFragment();
        toolbar.setNavigationOnClickListener( view -> {
            onBackPressed();
        });
        toolbar.setTitle(getResources().getString(R.string.auth));
        loadingView = new MaterialDialog.Builder(this)
                .content(R.string.please_wait)
                .progress(true, 0)
                .build();
    }

    @Override
    public void onBackPressed() {
        onUpdateToolbar();
        super.onBackPressed();
    }

    @Override
    public void onSetDefaultFragment() {
        onReplaceFragmentWithoutBackStack(new LoginFragment());
    }

    @Override
    public void onReplaceFragmentWithBackStack(@NonNull final Fragment fragment) {
        mFragmentManager.beginTransaction()
                .setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right)
                .replace(FRAGMENT_CONTAINER_ID, fragment)
                .addToBackStack(fragment.getClass().getSimpleName())
                .commit();
    }

    @Override
    public void onReplaceFragmentWithoutBackStack(@NonNull Fragment fragment) {
        mFragmentManager.beginTransaction()
                .setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right)
                .replace(FRAGMENT_CONTAINER_ID, fragment)
                .commit();
    }

    @Override
    public void onUpdateToolbar() {
        if (mFragmentManager.getBackStackEntryCount() > 0) {
            if (getSupportActionBar() != null) {
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                getSupportActionBar().setHomeButtonEnabled(true);
            }
        } else {
            if (getSupportActionBar() != null) {
                getSupportActionBar().setDisplayHomeAsUpEnabled(false);
                getSupportActionBar().setHomeButtonEnabled(false);
            }
        }
    }

    @Override
    public void onSetToolbarTitle(@NonNull String title) {
        toolbar.setTitle(title);
    }

    @Override
    public void onStartMainActivity() {
        final Intent intent = new Intent(this, MessengerMainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    @Override
    public void onShowLoadingView() {
        if (loadingView != null)
            loadingView.show();
    }

    @Override
    public void onHideLoadingView() {
        if (loadingView != null)
            loadingView.dismiss();
    }

    @Override
    public void onShowNetworkError(@NonNull String errorMsg) {
        Toast.makeText(this, errorMsg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        loadingView = null;
    }

}
