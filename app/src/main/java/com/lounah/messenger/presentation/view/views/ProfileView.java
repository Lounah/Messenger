package com.lounah.messenger.presentation.view.views;

import android.support.annotation.NonNull;

import com.lounah.messenger.presentation.model.posts.Post;

import java.util.List;

public interface ProfileView extends NetworkView {


    void onShowAvatar();

    void onLikePostButtonClicked();

    void onBookmarkPostButtonClicked();

    void onDeletePostButtonClicked();

    void onShowPosts(@NonNull final List<Post> posts);

}
