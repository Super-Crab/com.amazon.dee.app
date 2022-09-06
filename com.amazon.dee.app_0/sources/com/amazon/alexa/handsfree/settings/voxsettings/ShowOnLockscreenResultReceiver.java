package com.amazon.alexa.handsfree.settings.voxsettings;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.ResultReceiver;
import androidx.annotation.NonNull;
import com.amazon.alexa.handsfree.protocols.utils.Log;
import com.amazon.alexa.handsfree.settings.contract.handsfreesettings.PreferenceCallback;
import com.amazon.alexa.handsfree.settings.contract.handsfreesettings.ResponsePreferenceCallback;
/* loaded from: classes8.dex */
public class ShowOnLockscreenResultReceiver extends ResultReceiver {
    private static final String TAG = "ShowOnLSResultRcvr";
    private final boolean mBlockSensitiveRequest;
    private final PreferenceCallback mPreferenceCallback;
    private final ResponsePreferenceCallback mResponsePreferenceCallback;

    public ShowOnLockscreenResultReceiver(@NonNull ResponsePreferenceCallback responsePreferenceCallback) {
        super(new Handler(Looper.getMainLooper()));
        this.mResponsePreferenceCallback = responsePreferenceCallback;
        this.mPreferenceCallback = null;
        this.mBlockSensitiveRequest = true;
    }

    public PreferenceCallback getPreferenceCallback() {
        return this.mPreferenceCallback;
    }

    public ResponsePreferenceCallback getResponsePreferenceCallback() {
        return this.mResponsePreferenceCallback;
    }

    @Override // android.os.ResultReceiver
    protected void onReceiveResult(int i, Bundle bundle) {
        if (this.mBlockSensitiveRequest) {
            String string = bundle.getString(VoxSettingsRequestHandler.SHOW_ON_LOCKSCREEN_PREF_VALUE_KEY);
            if (i == 200) {
                Log.i(TAG, "Show on lockscreen preference value retrieved correctly. Value: " + string);
                this.mResponsePreferenceCallback.onPreferenceChanged(string);
                return;
            } else if (i == 500) {
                Log.w(TAG, "Failed to retrieve Show on lockscreen preference value, using last stored local value: " + string);
                this.mResponsePreferenceCallback.onPreferenceChanged(string);
                return;
            } else {
                Log.w(TAG, "Unknown result code in result receiver: " + i);
                return;
            }
        }
        boolean z = bundle.getBoolean(VoxSettingsRequestHandler.SHOW_ON_LOCKSCREEN_PREF_KEY);
        if (i == 200) {
            Log.i(TAG, "Show on lockscreen preference retrieved correctly. Value: " + z);
            this.mPreferenceCallback.onPreferenceChanged(z);
        } else if (i == 500) {
            Log.w(TAG, "Failed to retrieve Show on lockscreen preference, using last stored local value: " + z);
            this.mPreferenceCallback.onPreferenceChanged(z);
        } else {
            Log.w(TAG, "Unknown result code in result receiver: " + i);
        }
    }

    public ShowOnLockscreenResultReceiver(@NonNull PreferenceCallback preferenceCallback) {
        super(new Handler(Looper.getMainLooper()));
        this.mPreferenceCallback = preferenceCallback;
        this.mResponsePreferenceCallback = null;
        this.mBlockSensitiveRequest = false;
    }
}
