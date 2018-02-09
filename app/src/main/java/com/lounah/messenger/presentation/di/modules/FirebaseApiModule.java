package com.lounah.messenger.presentation.di.modules;


import com.lounah.messenger.data.Api;
import com.lounah.messenger.data.firebase.FirebaseApi;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class FirebaseApiModule {

    @Provides
    @Singleton
    Api provideApi() {
        return new FirebaseApi();
    }

}
