package com.amazon.alexa.handsfree.remotecontentservice.apigateway.remoteconfig.provider;

import android.content.Context;
import android.content.SharedPreferences;
import androidx.annotation.VisibleForTesting;
/* loaded from: classes8.dex */
public class HandsFreeSettingsStateProvider {
    private static final HandsFreeSettingsState DEFAULT_SETTINGS_STATE = HandsFreeSettingsState.LOCALLY_DISABLED;
    @VisibleForTesting
    static final String KEY_PREVIOUS_SETTINGS_STATE = "com.amazon.alexa.handsfree.settings.HandsFree.SETTINGS";
    @VisibleForTesting
    static final String SHARED_PREFERENCES_FILE_NAME = "com.amazon.alexa.handsfree.settings.HandsFree";
    private final SharedPreferences mSharedPreferences;

    public HandsFreeSettingsStateProvider(Context context) {
        this.mSharedPreferences = context.getSharedPreferences(SHARED_PREFERENCES_FILE_NAME, 0);
    }

    public HandsFreeSettingsState getPreviousSettingsState() {
        return HandsFreeSettingsState.get(this.mSharedPreferences.getInt(KEY_PREVIOUS_SETTINGS_STATE, DEFAULT_SETTINGS_STATE.getValue()));
    }

    public void setSettingsState(HandsFreeSettingsState handsFreeSettingsState) {
        this.mSharedPreferences.edit().putInt(KEY_PREVIOUS_SETTINGS_STATE, handsFreeSettingsState.getValue()).apply();
    }
}
