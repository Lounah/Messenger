package com.lounah.messenger.presentation.model;

import android.support.annotation.NonNull;

public class ServerResponse {

    private int code;
    private String message;

    public ServerResponse() {}

    public ServerResponse(@NonNull final int code, @NonNull final String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public static ServerResponse withCode(@NonNull final int code) {
        return new ServerResponse(200, "OK");
    }

}
