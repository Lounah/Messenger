package com.lounah.messenger.utils;

import android.support.annotation.NonNull;

import java.util.Arrays;
import java.util.List;

public class CredentialsReviewer {

    private static final List<CharSequence> bannedChars =
            Arrays.asList("!", "@", "#", "№", "$", "%", ":", "^",
                    ",", "&", ".", "*", ";", "(", ")", "_",
                    "-", "+", "=", "±", "§", ">", "<", "/",
                    "?", "'", "|");


    private CredentialsReviewer() {

    }

    public static boolean review(@NonNull final String username, @NonNull final String password) {
        return isUsernameCorrect(username) && isPasswordCorrect(password);
    }

    public static boolean review(@NonNull final String username, @NonNull final String password, @NonNull final String rePassword) {
        return isUsernameCorrect(username) && isPasswordCorrect(password) && (password.equals(rePassword));
    }

    private static boolean isUsernameCorrect(@NonNull final String username) {
        return (username.length() > 5) && (!textContainsAtLeastOneOf(username, bannedChars));
    }

    private static boolean isPasswordCorrect(@NonNull final String password) {
        return (password.length() > 5) && (!textContainsAtLeastOneOf(password, bannedChars));
    }

    private static boolean textContainsAtLeastOneOf(String text, List<CharSequence> bannedChars) {
        boolean result = false;
        for (CharSequence bannedChar : bannedChars) {
            if (text.contains(bannedChar)) result = true;
        }
        return result;
    }

}
