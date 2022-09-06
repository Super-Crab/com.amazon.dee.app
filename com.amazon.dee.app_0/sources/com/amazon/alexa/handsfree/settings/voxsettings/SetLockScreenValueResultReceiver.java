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
public class SetLockScreenValueResultReceiver extends ResultReceiver {
    private static final String TAG = SetLockScreenValueResultReceiver.class.getSimpleName();
    private final boolean mBlockSensitiveRequest;
    private final PreferenceCallback mPreferenceCallback;
    private final ResponsePreferenceCallback mResponsePreferenceCallback;

    public SetLockScreenValueResultReceiver(@NonNull ResponsePreferenceCallback responsePreferenceCallback) {
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
                String str = TAG;
                Log.i(str, "Show on lockscreen preference value set correctly. Value: " + string);
                this.mResponsePreferenceCallback.onPreferenceChanged(string);
                return;
            } else if (i == 500) {
                String str2 = TAG;
                Log.w(str2, "Failed to set Show on lockscreen preference value, using last stored local value: " + string);
                this.mResponsePreferenceCallback.onPreferenceChanged(string);
                return;
            } else {
                String str3 = TAG;
                Log.w(str3, "Unknown result code in result receiver: " + i);
                return;
            }
        }
        boolean z = bundle.getBoolean(VoxSettingsRequestHandler.SHOW_ON_LOCKSCREEN_PREF_KEY);
        if (i == 200) {
            String str4 = TAG;
            Log.i(str4, "Show on lockscreen preference set correctly. Value: " + z);
            this.mPreferenceCallback.onPreferenceChanged(z);
        } else if (i == 500) {
            String str5 = TAG;
            Log.w(str5, "Failed to set Show on lockscreen preference, using last stored local value: " + z);
            this.mPreferenceCallback.onPreferenceChanged(z);
        } else {
            String str6 = TAG;
            Log.w(str6, "Unknown result code in result receiver: " + i);
        }
    }

    public SetLockScreenValueResultReceiver(@NonNull PreferenceCallback preferenceCallback) {
        super(new Handler(Looper.getMainLooper()));
        this.mPreferenceCallback = preferenceCallback;
        this.mResponsePreferenceCallback = null;
        this.mBlockSensitiveRequest = false;
    }
}
