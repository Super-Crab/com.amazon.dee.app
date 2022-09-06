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
/* loaded from: classes8.dex */
public class VoxSettingsSetRequestHandler implements VoxSettingsRequestHandler {
    static final String EXTRA_RETRY_ATTEMPT = "retryAttempt";
    static final int NO_RETRY_INFO = -1;
    static final String RESULT_RECEIVER_EXTRA = "com.amazon.alexa.handsfree.voxconfig.SET_RESULT_RECEIVER";
    static final int RESULT_RECEIVER_FAILURE = 500;
    static final int RESULT_RECEIVER_SUCCESS = 200;
    private static final String TAG = "VoxSettingsSetRequestHandler";
    private final AlexaAudioProviderConnection mAlexaAudioProviderConnection;
    private ResultReceiver mResultReceiver;
    private int mRetryAttempt;
    private final SharedPreferences mSharedPreferences;
    private boolean mShowOnLockscreenValue;
    private String mShowOnLockscreenValueLevels;
    private final VoxSettingsSetRequestRetryPolicy mVoxSettingsSetRequestRetryPolicy;

    /* JADX INFO: Access modifiers changed from: package-private */
    public VoxSettingsSetRequestHandler(@NonNull AlexaAudioProviderConnection alexaAudioProviderConnection, @NonNull SharedPreferences sharedPreferences, @NonNull VoxSettingsSetRequestRetryPolicy voxSettingsSetRequestRetryPolicy) {
        this(alexaAudioProviderConnection, sharedPreferences, voxSettingsSetRequestRetryPolicy, -1, null);
    }

    @Override // com.amazon.alexa.handsfree.settings.voxsettings.VoxSettingsRequestHandler
    public boolean initialize(@NonNull Intent intent) {
        this.mRetryAttempt = intent.getIntExtra(EXTRA_RETRY_ATTEMPT, -1);
        if (intent.hasExtra(RESULT_RECEIVER_EXTRA) && (intent.getParcelableExtra(RESULT_RECEIVER_EXTRA) instanceof ResultReceiver)) {
            this.mResultReceiver = (ResultReceiver) intent.getParcelableExtra(RESULT_RECEIVER_EXTRA);
        }
        if (intent.hasExtra(VoxSettingsRequestHandler.SHOW_ON_LOCKSCREEN_PREF_VALUE_KEY)) {
            this.mShowOnLockscreenValueLevels = intent.getStringExtra(VoxSettingsRequestHandler.SHOW_ON_LOCKSCREEN_PREF_VALUE_KEY);
            return true;
        } else if (intent.hasExtra(VoxSettingsRequestHandler.SHOW_ON_LOCKSCREEN_PREF_KEY)) {
            this.mShowOnLockscreenValue = intent.getBooleanExtra(VoxSettingsRequestHandler.SHOW_ON_LOCKSCREEN_PREF_KEY, false);
            return true;
        } else if (this.mSharedPreferences.contains(VoxSettingsRequestHandler.SHOW_ON_LOCKSCREEN_PREF_VALUE_KEY)) {
            this.mShowOnLockscreenValueLevels = this.mSharedPreferences.getString(VoxSettingsRequestHandler.SHOW_ON_LOCKSCREEN_PREF_VALUE_KEY, VoxSettingsRequestHandler.SHOW_ON_LOCKSCREEN_PREF_VALUE_BLOCK_PERSONAL);
            return true;
        } else if (!this.mSharedPreferences.contains(VoxSettingsRequestHandler.SHOW_ON_LOCKSCREEN_PREF_KEY)) {
            return false;
        } else {
            this.mShowOnLockscreenValue = this.mSharedPreferences.getBoolean(VoxSettingsRequestHandler.SHOW_ON_LOCKSCREEN_PREF_KEY, false);
            return true;
        }
    }

    @Override // com.amazon.alexa.handsfree.settings.voxsettings.VoxSettingsRequestHandler
    public String onConnectedToService() {
        AlexaPreferences.ShowOnLockscreenWithVerifiedVoiceValue[] values;
        AlexaPreferences.ShowOnLockscreenWithVerifiedVoiceValue showOnLockscreenWithVerifiedVoiceValue = AlexaPreferences.ShowOnLockscreenWithVerifiedVoiceValue.BLOCK_PERSONAL;
        for (AlexaPreferences.ShowOnLockscreenWithVerifiedVoiceValue showOnLockscreenWithVerifiedVoiceValue2 : AlexaPreferences.ShowOnLockscreenWithVerifiedVoiceValue.values()) {
            if (showOnLockscreenWithVerifiedVoiceValue2.toString().equals(this.mShowOnLockscreenValueLevels)) {
                showOnLockscreenWithVerifiedVoiceValue = showOnLockscreenWithVerifiedVoiceValue2;
            }
        }
        DevicePreferences.updatePreferences(this.mAlexaAudioProviderConnection, AlexaPreferences.builder().setPreferDisplayOverLockscreenWithVerifiedVoice(showOnLockscreenWithVerifiedVoiceValue).build());
        return this.mShowOnLockscreenValueLevels;
    }

    @Override // com.amazon.alexa.handsfree.settings.voxsettings.VoxSettingsRequestHandler
    public boolean onConnectedToServiceLegacy() {
        DevicePreferences.updatePreferences(this.mAlexaAudioProviderConnection, AlexaPreferences.builder().setPreferDisplayOverLockscreenWithVerifiedVoice(this.mShowOnLockscreenValue ? AlexaPreferences.ShowOnLockscreenWithVerifiedVoiceValue.BLOCK_NONE : AlexaPreferences.ShowOnLockscreenWithVerifiedVoiceValue.BLOCK_ALL).build());
        return this.mShowOnLockscreenValue;
    }

    @Override // com.amazon.alexa.handsfree.settings.voxsettings.VoxSettingsRequestHandler
    public void onRequestFailed(boolean z) {
        Log.w(TAG, "Failed to update preference. Storing in shared prefs for retrying.");
        Bundle bundle = new Bundle();
        if (z) {
            this.mSharedPreferences.edit().putString(VoxSettingsRequestHandler.SHOW_ON_LOCKSCREEN_PREF_VALUE_KEY, this.mShowOnLockscreenValueLevels).apply();
            bundle.putString(VoxSettingsRequestHandler.SHOW_ON_LOCKSCREEN_PREF_VALUE_KEY, this.mShowOnLockscreenValueLevels);
        } else {
            this.mSharedPreferences.edit().putBoolean(VoxSettingsRequestHandler.SHOW_ON_LOCKSCREEN_PREF_KEY, this.mShowOnLockscreenValue).apply();
            bundle.putBoolean(VoxSettingsRequestHandler.SHOW_ON_LOCKSCREEN_PREF_KEY, this.mShowOnLockscreenValue);
        }
        int i = this.mRetryAttempt;
        if (i != -1) {
            this.mVoxSettingsSetRequestRetryPolicy.scheduleRetry(i + 1);
        }
        ResultReceiver resultReceiver = this.mResultReceiver;
        if (resultReceiver != null) {
            resultReceiver.send(500, bundle);
        }
    }

    @Override // com.amazon.alexa.handsfree.settings.voxsettings.VoxSettingsRequestHandler
    public void onRequestSucceeded(String str) {
        Log.v(TAG, "Successfully synced preference to Vox.");
        Bundle bundle = new Bundle();
        bundle.putString(VoxSettingsRequestHandler.SHOW_ON_LOCKSCREEN_PREF_VALUE_KEY, str);
        if (this.mSharedPreferences.contains(VoxSettingsRequestHandler.SHOW_ON_LOCKSCREEN_PREF_VALUE_KEY)) {
            this.mSharedPreferences.edit().remove(VoxSettingsRequestHandler.SHOW_ON_LOCKSCREEN_PREF_VALUE_KEY).apply();
        }
        ResultReceiver resultReceiver = this.mResultReceiver;
        if (resultReceiver != null) {
            resultReceiver.send(200, bundle);
        }
    }

    @VisibleForTesting
    VoxSettingsSetRequestHandler(@NonNull AlexaAudioProviderConnection alexaAudioProviderConnection, @NonNull SharedPreferences sharedPreferences, @NonNull VoxSettingsSetRequestRetryPolicy voxSettingsSetRequestRetryPolicy, int i, @Nullable ResultReceiver resultReceiver) {
        this.mShowOnLockscreenValueLevels = null;
        this.mAlexaAudioProviderConnection = alexaAudioProviderConnection;
        this.mSharedPreferences = sharedPreferences;
        this.mVoxSettingsSetRequestRetryPolicy = voxSettingsSetRequestRetryPolicy;
        this.mRetryAttempt = i;
        this.mResultReceiver = resultReceiver;
    }

    @Override // com.amazon.alexa.handsfree.settings.voxsettings.VoxSettingsRequestHandler
    public void onRequestSucceeded(boolean z) {
        Log.v(TAG, "Successfully synced preference to Vox.");
        Bundle bundle = new Bundle();
        bundle.putBoolean(VoxSettingsRequestHandler.SHOW_ON_LOCKSCREEN_PREF_KEY, z);
        if (this.mSharedPreferences.contains(VoxSettingsRequestHandler.SHOW_ON_LOCKSCREEN_PREF_KEY)) {
            this.mSharedPreferences.edit().remove(VoxSettingsRequestHandler.SHOW_ON_LOCKSCREEN_PREF_KEY).apply();
        }
        ResultReceiver resultReceiver = this.mResultReceiver;
        if (resultReceiver != null) {
            resultReceiver.send(200, bundle);
        }
    }
}
