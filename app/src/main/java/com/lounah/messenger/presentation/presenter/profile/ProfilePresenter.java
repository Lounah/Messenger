package com.lounah.messenger.presentation.presenter.profile;


import android.support.annotation.NonNull;

import com.lounah.messenger.presentation.model.posts.Post;
import com.lounah.messenger.presentation.model.posts.PostType;
import com.lounah.messenger.presentation.presenter.BasePresenter;
import com.lounah.messenger.presentation.view.views.ProfileView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class ProfilePresenter extends BasePresenter<ProfileView> {

    private ProfileView mView;

    @Inject
    public ProfilePresenter() {

    }

    @Override
    public void setView(@NonNull ProfileView view) {
        mView = view;
        getPosts();
    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void destroy() {
        mView = null;
    }

    private void getPosts() {
        List<Post> result = new ArrayList<>();

        result.add(new Post(0, PostType.PHOTO_WITH_TEXT, "Мое первое фото для мессенджера", "https://www.aflow.ru/image/cache/catalog/foto/9/786045263-vishnya-seyanets-lyubskoj-1200x800.jpg", "Lounah", "10.01.17 23:42", 777, false, false));
        result.add(new Post(1, PostType.TEXT, "Артем пососет", "Null", "Lounah", "10.01.17 23:03", 544, false, false));
        result.add(new Post(2, PostType.TEXT, "Ваня пососет", "Null", "Lounah", "10.01.17 23:02", 344, false, false));
        result.add(new Post(3, PostType.TEXT, "Привет, это мое первое сообщение здесь.", "Null", "Lounah", "10.01.17 23:01", 144, false, false));

        mView.onShowPosts(result);
    }
}

// https://vk.com/photo432218331_456239037