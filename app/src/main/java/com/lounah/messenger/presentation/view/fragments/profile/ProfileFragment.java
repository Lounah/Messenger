package com.lounah.messenger.presentation.view.fragments.profile;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.lounah.messenger.R;
import com.lounah.messenger.presentation.di.MessengerApplication;
import com.lounah.messenger.presentation.di.scopes.ActivityScoped;
import com.lounah.messenger.presentation.model.posts.Post;
import com.lounah.messenger.presentation.presenter.profile.ProfilePresenter;
import com.lounah.messenger.presentation.view.activities.MessengerMainActivity;
import com.lounah.messenger.presentation.view.adapters.PostsAdapter;
import com.lounah.messenger.presentation.view.fragments.BaseFragment;
import com.lounah.messenger.presentation.view.views.ProfileView;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

@ActivityScoped
public class ProfileFragment extends BaseFragment implements ProfileView {

    private PostsAdapter adapter;
    private LinearLayoutManager linearLayoutManager;

    @BindView(R.id.posts_rv)
    RecyclerView postsRv;

    @BindView(R.id.avatar_iv)
    ImageView avatarIv;

    @BindView(R.id.add_photo)
    ImageButton changePhotoIv;

    @BindView(R.id.username_tv)
    TextView usernameTv;

    @BindView(R.id.edit_username)
    ImageButton editUsernameIb;

    @BindView(R.id.friends_tv)
    TextView friendsTv;

    @BindView(R.id.subscribers_tv)
    TextView subscribersTv;

    @BindView(R.id.more_tv)
    TextView moreTv;

    @BindView(R.id.location_tv)
    TextView locationTv;

    @BindView(R.id.edit_profile_button)
    Button editProfileBtn;

    @BindView(R.id.add_post_tv)
    TextView addPostTv;

    @BindView(R.id.add_photo_tv)
    TextView addPhotoTv;


    @Inject
    ProfilePresenter presenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(false);
        MessengerApplication.getProfileComponent().inject(this);
        adapter = new PostsAdapter(getContext());
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        setToolbarTitle();
        ((MessengerMainActivity) getActivity()).onUpdateToolbar();
        return view;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        postsRv.setAdapter(adapter);
        linearLayoutManager = new LinearLayoutManager(getContext());
        postsRv.setLayoutManager(linearLayoutManager);
        presenter.setView(this);
    }

    @Override
    protected void setToolbarTitle() {
        ((MessengerMainActivity)getActivity()).setToolbarTitle(getResources().getString(R.string.bottom_navigation_item_profile));
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.setView(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.destroy();
    }

    @Override
    public boolean isNested() {
        return false;
    }

    @Override
    protected void onMenuItemClicked() {

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
    public void onShowAvatar() {

    }

    @Override
    public void onLikePostButtonClicked() {

    }

    @Override
    public void onBookmarkPostButtonClicked() {

    }

    @Override
    public void onDeletePostButtonClicked() {

    }

    @Override
    public void onShowPosts(@NonNull List<Post> posts) {
        adapter.updateDataSet(posts);
    }


    @OnClick(R.id.avatar_iv)
    public void onAvatarIvClicked() {

    }


    @OnClick(R.id.add_photo)
    public void onAddPhotoIbClicked() {

    }


    @OnClick(R.id.username_tv)
    public void onUsernameTvClicked() {

    }

    @OnClick(R.id.edit_username)
    public void onEditUsernameIbClicked() {

    }

    @OnClick(R.id.friends_tv)
    public void onFriendsTvClicked() {

    }

    @OnClick(R.id.subscribers_tv)
    public void onSubscribersTvClicked() {

    }

    @OnClick(R.id.location_tv)
    public void onLocationTvClicked() {

    }

    @OnClick(R.id.more_tv)
    public void onMoreTvClicked() {

    }

    @OnClick(R.id.edit_profile_button)
    public void onEditProfileBtnClicked() {

    }

    @OnClick(R.id.add_post_tv)
    public void onAddPostTvClicked() {

    }

    @OnClick(R.id.add_photo_tv)
    public void onAddPhotoTvClicked() {

    }


}
