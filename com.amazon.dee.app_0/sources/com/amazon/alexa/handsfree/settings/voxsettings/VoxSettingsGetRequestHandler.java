package com.amazon.alexa.handsfree.settings.voxsettings;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.ResultReceiver;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.api.AlexaAudioProviderConnection;
import com.amazon.alexa.api.AlexaPreferences;
import com.amazon.alexa.api.DevicePreferences;
import com.amazon.alexa.handsfree.protocols.utils.Log;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes8.dex */
public class VoxSettingsGetRequestHandler implements VoxSettingsRequestHandler {
    static final String RESULT_RECEIVER_EXTRA = "com.amazon.alexa.handsfree.voxconfig.EXTRA_RESULT_RECEIVER";
    static final int RESULT_RECEIVER_FAILURE = 500;
    static final int RESULT_RECEIVER_SUCCESS = 200;
    private static final String TAG = "VoxSettingsGetRequestHandler";
    private final AlexaAudioProviderConnection mAlexaAudioProviderConnection;
    private ResultReceiver mResultReceiver;
    private final SharedPreferences mSharedPreferences;

    /* JADX INFO: Access modifiers changed from: package-private */
    public VoxSettingsGetRequestHandler(@NonNull AlexaAudioProviderConnection alexaAudioProviderConnection, @NonNull SharedPreferences sharedPreferences) {
        this(alexaAudioProviderConnection, sharedPreferences, null);
    }

    public String checkLegacyValue() {
        return this.mSharedPreferences.getBoolean(VoxSettingsRequestHandler.SHOW_ON_LOCKSCREEN_PREF_KEY, true) ? VoxSettingsRequestHandler.SHOW_ON_LOCKSCREEN_PREF_VALUE_BLOCK_PERSONAL : VoxSettingsRequestHandler.SHOW_ON_LOCKSCREEN_PREF_VALUE_BLOCK_ALL;
    }

    @Override // com.amazon.alexa.handsfree.settings.voxsettings.VoxSettingsRequestHandler
    public boolean initialize(@NonNull Intent intent) {
        if (!intent.hasExtra(RESULT_RECEIVER_EXTRA) || !(intent.getParcelableExtra(RESULT_RECEIVER_EXTRA) instanceof ResultReceiver)) {
            return false;
        }
        this.mResultReceiver = (ResultReceiver) intent.getParcelableExtra(RESULT_RECEIVER_EXTRA);
        return true;
    }

    @Override // com.amazon.alexa.handsfree.settings.voxsettings.VoxSettingsRequestHandler
    public String onConnectedToService() {
        String preferDisplayOverLockscreenWithVerifiedVoiceValue;
        AlexaPreferences preferences = DevicePreferences.getPreferences(this.mAlexaAudioProviderConnection);
        if (preferences.preferDisplayOverLockscreenWithVerifiedVoiceValue() == null) {
            preferDisplayOverLockscreenWithVerifiedVoiceValue = checkLegacyValue();
        } else {
            preferDisplayOverLockscreenWithVerifiedVoiceValue = preferences.preferDisplayOverLockscreenWithVerifiedVoiceValue();
        }
        String str = TAG;
        Log.v(str, "Connected to Vox. Retrieved: showOnLockscreenValue: " + preferDisplayOverLockscreenWithVerifiedVoiceValue);
        return preferDisplayOverLockscreenWithVerifiedVoiceValue;
    }

    @Override // com.amazon.alexa.handsfree.settings.voxsettings.VoxSettingsRequestHandler
    public boolean onConnectedToServiceLegacy() {
        AlexaPreferences preferences = DevicePreferences.getPreferences(this.mAlexaAudioProviderConnection);
        boolean equals = preferences.preferDisplayOverLockscreenWithVerifiedVoiceValue().equals(AlexaPreferences.ShowOnLockscreenWithVerifiedVoiceValue.BLOCK_NONE.name());
        String str = TAG;
        Log.v(str, "Connected to Vox. Retrieved: showOnLockscreen: " + equals + " for " + preferences.preferDisplayOverLockscreenWithVerifiedVoiceValue());
        return equals;
    }

    @Override // com.amazon.alexa.handsfree.settings.voxsettings.VoxSettingsRequestHandler
    public void onRequestFailed(boolean z) {
        Bundle bundle = new Bundle();
        if (z) {
            String string = this.mSharedPreferences.getString(VoxSettingsRequestHandler.SHOW_ON_LOCKSCREEN_PREF_VALUE_KEY, VoxSettingsRequestHandler.SHOW_ON_LOCKSCREEN_PREF_VALUE_BLOCK_PERSONAL);
            String str = TAG;
            Log.v(str, "Failed to connect to Vox. Using latest value from SharedPrefs: " + string);
            bundle.putString(VoxSettingsRequestHandler.SHOW_ON_LOCKSCREEN_PREF_VALUE_KEY, string);
            this.mResultReceiver.send(500, bundle);
            return;
        }
        boolean z2 = this.mSharedPreferences.getBoolean(VoxSettingsRequestHandler.SHOW_ON_LOCKSCREEN_PREF_KEY, false);
        String str2 = TAG;
        Log.v(str2, "Failed to connect to Vox. Using latest value from SharedPrefs: " + z2);
        bundle.putBoolean(VoxSettingsRequestHandler.SHOW_ON_LOCKSCREEN_PREF_KEY, z2);
        this.mResultReceiver.send(500, bundle);
    }

    @Override // com.amazon.alexa.handsfree.settings.voxsettings.VoxSettingsRequestHandler
    public void onRequestSucceeded(String str) {
        Bundle outline11 = GeneratedOutlineSupport1.outline11(VoxSettingsRequestHandler.SHOW_ON_LOCKSCREEN_PREF_VALUE_KEY, str);
        this.mSharedPreferences.edit().putString(VoxSettingsRequestHandler.SHOW_ON_LOCKSCREEN_PREF_VALUE_KEY, str).apply();
        this.mResultReceiver.send(200, outline11);
    }

    @VisibleForTesting
    VoxSettingsGetRequestHandler(@NonNull AlexaAudioProviderConnection alexaAudioProviderConnection, @NonNull SharedPreferences sharedPreferences, @Nullable ResultReceiver resultReceiver) {
        this.mAlexaAudioProviderConnection = alexaAudioProviderConnection;
        this.mSharedPreferences = sharedPreferences;
        this.mResultReceiver = resultReceiver;
    }

    @Override // com.amazon.alexa.handsfree.settings.voxsettings.VoxSettingsRequestHandler
    public void onRequestSucceeded(boolean z) {
        Bundle outline13 = GeneratedOutlineSupport1.outline13(VoxSettingsRequestHandler.SHOW_ON_LOCKSCREEN_PREF_KEY, z);
        GeneratedOutlineSupport1.outline143(this.mSharedPreferences, VoxSettingsRequestHandler.SHOW_ON_LOCKSCREEN_PREF_KEY, z);
        this.mResultReceiver.send(200, outline13);
    }
}
