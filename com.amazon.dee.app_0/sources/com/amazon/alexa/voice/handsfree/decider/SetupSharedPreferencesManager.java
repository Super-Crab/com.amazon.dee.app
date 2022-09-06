package com.amazon.alexa.voice.handsfree.decider;

import android.content.Context;
import android.content.SharedPreferences;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
/* loaded from: classes11.dex */
public class SetupSharedPreferencesManager {
    @VisibleForTesting
    static final int MAX_SETUP_NUMBER = 3;
    @VisibleForTesting
    static final String SETUP_SHOWN_COUNT_KEY = "setup_shown_count_key";
    private static final String SHARED_PREFS_FILE_NAME = "setup_shown_count";
    private final SharedPreferences mSharedPreferences;

    public SetupSharedPreferencesManager(@NonNull Context context) {
        this(context.getSharedPreferences(SHARED_PREFS_FILE_NAME, 0));
    }

    @VisibleForTesting
    synchronized int getSetupShownCount() {
        return this.mSharedPreferences.getInt(SETUP_SHOWN_COUNT_KEY, 0);
    }

    public synchronized void increaseSetupShownCount() {
        SharedPreferences.Editor edit = this.mSharedPreferences.edit();
        edit.putInt(SETUP_SHOWN_COUNT_KEY, getSetupShownCount() + 1);
        edit.apply();
    }

    public boolean isSetupShownCountLimitReached() {
        return getSetupShownCount() >= 3;
    }

    @VisibleForTesting
    SetupSharedPreferencesManager(@NonNull SharedPreferences sharedPreferences) {
        this.mSharedPreferences = sharedPreferences;
    }
}
