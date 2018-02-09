package com.lounah.messenger.presentation.view.views;


import android.support.annotation.NonNull;

public interface DialogsView extends NetworkView {

    void onOpenConversation(@NonNull final String conversationID);

}
