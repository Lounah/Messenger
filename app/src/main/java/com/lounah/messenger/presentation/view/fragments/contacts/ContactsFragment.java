package com.lounah.messenger.presentation.view.fragments.contacts;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lounah.messenger.R;
import com.lounah.messenger.presentation.di.scopes.ActivityScoped;
import com.lounah.messenger.presentation.view.activities.MessengerMainActivity;
import com.lounah.messenger.presentation.view.fragments.BaseFragment;

import butterknife.ButterKnife;

@ActivityScoped
public class ContactsFragment extends BaseFragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(false);
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_contacts, container, false);
        setToolbarTitle();
        ((MessengerMainActivity) getActivity()).onUpdateToolbar();
        return view;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
    }

    @Override
    protected void setToolbarTitle() {
        ((MessengerMainActivity)getActivity()).setToolbarTitle(getResources().getString(R.string.bottom_navigation_item_contacts));
    }

    @Override
    public boolean isNested() {
        return false;
    }

    @Override
    protected void onMenuItemClicked() {

    }
}
