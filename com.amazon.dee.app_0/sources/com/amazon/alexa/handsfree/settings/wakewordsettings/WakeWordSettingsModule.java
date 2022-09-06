package com.amazon.alexa.handsfree.settings.wakewordsettings;

import androidx.annotation.Nullable;
import com.amazon.alexa.handsfree.protocols.utils.Log;
import com.amazon.alexa.handsfree.protocols.wakewordsettings.connection.WakeWordServiceConnectionProviderContract;
/* loaded from: classes8.dex */
public enum WakeWordSettingsModule {
    INSTANCE;
    
    private static final String TAG = "WakeWordSettingsModule";
    private boolean mIsInitialized;
    private WakeWordServiceConnectionProviderContract mWakeWordServiceConnectionProviderContract;

    @Nullable
    public WakeWordServiceConnectionProviderContract getWakeWordServiceConnectionProviderContract() {
        return this.mWakeWordServiceConnectionProviderContract;
    }

    public synchronized void initialize(@Nullable WakeWordServiceConnectionProviderContract wakeWordServiceConnectionProviderContract) {
        if (!this.mIsInitialized) {
            Log.d(TAG, "Not initialized, Initializing now");
            this.mWakeWordServiceConnectionProviderContract = wakeWordServiceConnectionProviderContract;
            this.mIsInitialized = true;
        }
    }

    public synchronized boolean isInitialized() {
        return this.mIsInitialized;
    }
}
