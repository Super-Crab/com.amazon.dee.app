package com.amazon.alexa.handsfree.protocols.settings;

import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
/* loaded from: classes8.dex */
public class WakeWordSettingsManagerProvider {
    private static final String TAG = "WakeWordSettingsManagerProvider";
    private WakeWordSettingsManager mWakeWordSettingsManager;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes8.dex */
    public static class InstanceHolder {
        private static final WakeWordSettingsManagerProvider INSTANCE = new WakeWordSettingsManagerProvider();

        private InstanceHolder() {
        }
    }

    @VisibleForTesting
    WakeWordSettingsManagerProvider() {
    }

    public static WakeWordSettingsManagerProvider getInstance() {
        return InstanceHolder.INSTANCE;
    }

    public WakeWordSettingsManager get() {
        WakeWordSettingsManager wakeWordSettingsManager = this.mWakeWordSettingsManager;
        if (wakeWordSettingsManager != null) {
            return wakeWordSettingsManager;
        }
        throw new IllegalStateException("WakeWordSettingsManager wasn't initialized.");
    }

    public synchronized void initialize(@NonNull WakeWordSettingsManager wakeWordSettingsManager) {
        this.mWakeWordSettingsManager = wakeWordSettingsManager;
    }
}
