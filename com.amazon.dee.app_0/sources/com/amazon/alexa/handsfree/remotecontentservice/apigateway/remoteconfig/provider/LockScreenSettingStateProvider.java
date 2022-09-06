package com.amazon.alexa.handsfree.remotecontentservice.apigateway.remoteconfig.provider;

import android.content.Context;
import android.content.SharedPreferences;
import androidx.annotation.VisibleForTesting;
/* loaded from: classes8.dex */
public class LockScreenSettingStateProvider {
    private static final LockScreenSettingState DEFAULT_STATE = LockScreenSettingState.REMOTELY_DISABLED;
    @VisibleForTesting
    static final String KEY_PREVIOUS_LOCK_SCREEN_STATE = "com.amazon.alexa.handsfree.settings.LockScreen.SETTINGS";
    @VisibleForTesting
    static final String LOCK_SCREEN_SHARED_PREFERENCES_FILE_NAME = "com.amazon.alexa.handsfree.settings.LockScreen";
    private final SharedPreferences mSharedPreferences;

    public LockScreenSettingStateProvider(Context context) {
        this.mSharedPreferences = context.getSharedPreferences(LOCK_SCREEN_SHARED_PREFERENCES_FILE_NAME, 0);
    }

    public LockScreenSettingState getPreviousLockScreenSettingState() {
        return LockScreenSettingState.get(this.mSharedPreferences.getInt(KEY_PREVIOUS_LOCK_SCREEN_STATE, DEFAULT_STATE.getValue()));
    }

    public void setLockScreenSettingState(LockScreenSettingState lockScreenSettingState) {
        this.mSharedPreferences.edit().putInt(KEY_PREVIOUS_LOCK_SCREEN_STATE, lockScreenSettingState.getValue()).apply();
    }
}
