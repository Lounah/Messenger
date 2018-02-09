package com.lounah.messenger.presentation.view.fragments;

import android.content.Context;
import android.support.v4.app.Fragment;

public abstract class BaseFragment extends Fragment {

    protected abstract void setToolbarTitle();

    public abstract boolean isNested();

    protected abstract void onMenuItemClicked();

    protected FragmentNavigation mFragmentNavigation;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof FragmentNavigation) {
            mFragmentNavigation = (FragmentNavigation) context;
        }
    }

    public interface FragmentNavigation {
        void pushFragment(Fragment fragment);
    }


}
