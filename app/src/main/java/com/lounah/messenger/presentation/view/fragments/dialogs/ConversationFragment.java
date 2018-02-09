package com.lounah.messenger.presentation.view.fragments.dialogs;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lounah.messenger.R;
import com.lounah.messenger.presentation.di.scopes.ActivityScoped;
import com.lounah.messenger.presentation.view.activities.MessengerMainActivity;
import com.lounah.messenger.presentation.view.fragments.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

@ActivityScoped
public class ConversationFragment extends BaseFragment {

    @BindView(R.id.conversation_rv)
    RecyclerView conversationRv;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(false);
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_conversation, container, false);
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

    }

    @Override
    public boolean isNested() {
        return true;
    }

    @Override
    protected void onMenuItemClicked() {

    }
}
