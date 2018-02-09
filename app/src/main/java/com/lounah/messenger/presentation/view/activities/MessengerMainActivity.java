package com.lounah.messenger.presentation.view.activities;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import com.lounah.messenger.R;
import com.lounah.messenger.presentation.view.fragments.BaseFragment;
import com.lounah.messenger.presentation.view.fragments.contacts.ContactsFragment;
import com.lounah.messenger.presentation.view.fragments.dialogs.DialogsFragment;
import com.lounah.messenger.presentation.view.fragments.profile.ProfileFragment;
import com.lounah.messenger.presentation.view.fragments.tape.TapeFragment;
import com.lounah.messenger.presentation.view.views.MainView;
import com.ncapdevi.fragnav.FragNavController;
import com.ncapdevi.fragnav.FragNavSwitchController;
import com.ncapdevi.fragnav.FragNavTransactionOptions;
import com.ncapdevi.fragnav.tabhistory.FragNavTabHistoryController;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabReselectListener;
import com.roughike.bottombar.OnTabSelectListener;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MessengerMainActivity extends AppCompatActivity implements BaseFragment.FragmentNavigation,
        FragNavController.TransactionListener, FragNavController.RootFragmentListener, MainView {

    private static final int FRAGMENT_CONTAINER_ID = R.id.fragmentContainer;

    private final int INDEX_PROFILE = FragNavController.TAB1;
    private final int INDEX_DIALOGS = FragNavController.TAB2;
    private final int INDEX_CONTACTS = FragNavController.TAB3;
    private final int INDEX_TAPE = FragNavController.TAB4;

    private FragNavController mFragmentNavigationController;

    @BindView(R.id.main_toolbar)
    Toolbar toolbar;

    @BindView(R.id.bottom_navigation)
    BottomBar bottomNavigationBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initUI(savedInstanceState);
    }

    private void initUI(Bundle savedInstanceState) {

        boolean firstCreate = (savedInstanceState == null);

        ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        toolbar.setNavigationOnClickListener( view -> {
            onBackPressed();
        });

        if (firstCreate) {
            toolbar.setTitle(getResources().getString(R.string.bottom_navigation_item_dialogs));
            bottomNavigationBar.selectTabAtPosition(INDEX_DIALOGS);
        }

        buildFragmentNavigationController(savedInstanceState);

        bottomNavigationBar.setOnTabSelectListener(new BottomBarSelectItemListener(), firstCreate);
        bottomNavigationBar.setOnTabReselectListener(new BottomBarReselectItemListener());
    }

    @Override
    public void onBackPressed() {
        if (!mFragmentNavigationController.popFragment()) {
            super.onBackPressed();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (mFragmentNavigationController != null) {
            mFragmentNavigationController.onSaveInstanceState(outState);
        }
    }

    @Override
    public void pushFragment(Fragment fragment) {
        if (mFragmentNavigationController != null) {
            mFragmentNavigationController.pushFragment(fragment, FragNavTransactionOptions.newBuilder()
                    .customAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right)
                    .build());
        }
    }

    @Override
    public Fragment getRootFragment(int index) {
        Fragment root = null;
        switch (index) {
            case INDEX_CONTACTS:
                root =  new ContactsFragment();
                break;
            case INDEX_DIALOGS:
                root =  new DialogsFragment();
                break;
            case INDEX_TAPE:
                root =  new TapeFragment();
                break;
            case INDEX_PROFILE:
                root =  new ProfileFragment();
                break;
        }
        return root;
    }

    @Override
    public void onTabTransaction(@Nullable Fragment fragment, int i) {
        if (getSupportActionBar() != null && mFragmentNavigationController != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(!mFragmentNavigationController.isRootFragment());
        }
    }

    @Override
    public void onFragmentTransaction(Fragment fragment, FragNavController.TransactionType transactionType) {
        if (getSupportActionBar() != null && mFragmentNavigationController != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(!mFragmentNavigationController.isRootFragment());
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mFragmentNavigationController.popFragment();
                break;
        }
        return true;
    }


    private void buildFragmentNavigationController(@Nullable final Bundle savedInstanceState) {
        mFragmentNavigationController = FragNavController.newBuilder(savedInstanceState, getSupportFragmentManager(), FRAGMENT_CONTAINER_ID)
                .transactionListener(this)
                .rootFragmentListener(this, 4)
                .popStrategy(FragNavTabHistoryController.UNIQUE_TAB_HISTORY)
                .switchController(new FragNavSwitchController() {
                    @Override
                    public void switchTab(int index, FragNavTransactionOptions transactionOptions) {
                        bottomNavigationBar.selectTabAtPosition(index);
                    }
                })
                .build();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onShowLoadingView() {

    }

    @Override
    public void onHideLoadingView() {

    }

    @Override
    public void onShowNetworkError(@NonNull String errorMsg) {

    }

    @Override
    public void onShowError(@NonNull Throwable error) {

    }

    @Override
    public void onUpdateToolbar() {

    }

    @Override
    public void setToolbarTitle(@NonNull String title) {
        toolbar.setTitle(title);
    }

    @Override
    public void setToolbarSubtitle(@NonNull String subtitle) {
        toolbar.setSubtitle(subtitle);
    }

    private final class BottomBarSelectItemListener implements OnTabSelectListener {

        @Override
        public void onTabSelected(int tabId) {
            switch (tabId) {
                case R.id.action_contacts:
                    mFragmentNavigationController.switchTab(INDEX_CONTACTS);
                    break;
                case R.id.action_dialogs:
                    mFragmentNavigationController.switchTab(INDEX_DIALOGS);
                    break;
                case R.id.action_feed:
                    mFragmentNavigationController.switchTab(INDEX_TAPE);
                    break;
                case R.id.action_profile:
                    mFragmentNavigationController.switchTab(INDEX_PROFILE);
                    break;
            }
        }
    }

    private final class BottomBarReselectItemListener implements OnTabReselectListener {

        @Override
        public void onTabReSelected(int tabId) {
            mFragmentNavigationController.clearStack();
        }
    }


}
