package com.amazon.alexa.accessory.notificationpublisher.providers;

import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.accessory.Accessory;
import com.amazon.alexa.accessory.notificationpublisher.consumption.StatusEventManager;
import com.amazon.alexa.accessory.notificationpublisher.metrics.MetricsConstants;
import com.amazon.alexa.accessory.notificationpublisher.metrics.MetricsRecorder;
import com.amazon.alexa.accessory.notificationpublisher.storage.SettingsStorageModule;
import com.amazon.alexa.accessory.notificationpublisher.utils.Log;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
/* loaded from: classes.dex */
public class MultiDeviceDistractionModeProvider {
    private static final String TAG = "MultiDeviceDistractionModeProvider";
    private static volatile MultiDeviceDistractionModeProvider multiDeviceDistractionModeProvider;
    private Map<String, DistractionMode> distractionModeMap;
    private SettingsStorageModule settingsStorageModule;
    private StatusEventManager statusEventManager;

    private MultiDeviceDistractionModeProvider() {
        this.distractionModeMap = new ConcurrentHashMap();
    }

    public static MultiDeviceDistractionModeProvider getInstance() {
        if (multiDeviceDistractionModeProvider == null) {
            synchronized (MultiDeviceDistractionModeProvider.class) {
                if (multiDeviceDistractionModeProvider == null) {
                    multiDeviceDistractionModeProvider = new MultiDeviceDistractionModeProvider();
                }
            }
        }
        return multiDeviceDistractionModeProvider;
    }

    private synchronized void onAccessoryConnected(String str) {
        try {
            if (!this.distractionModeMap.containsKey(str)) {
                this.distractionModeMap.put(str, new DistractionMode(getSettingsStorageModule().getLowDistractionModeSettingWithDefault(str).booleanValue(), getSettingsStorageModule().getSilentDistractionModeSetting(str).booleanValue()));
            }
        } catch (Exception e) {
            Log.e(TAG, "creating distraction mode failed", e);
            MetricsRecorder.getInstance().recordCounter(MetricsConstants.DISTRACTION_MODE_CREATE_FAILURE, MetricsRecorder.customAttributesForDeviceTypeAndException(str, e));
        }
    }

    private synchronized void onAccessoryDisconnected(String str) {
        this.distractionModeMap.remove(str);
    }

    public synchronized int getCurrentDistractionMode() {
        int i;
        i = Integer.MAX_VALUE;
        for (DistractionMode distractionMode : this.distractionModeMap.values()) {
            int currentDistractionMode = distractionMode.getCurrentDistractionMode();
            if (i > currentDistractionMode) {
                i = currentDistractionMode;
            }
        }
        if (i == Integer.MAX_VALUE) {
            i = 1;
        }
        return i;
    }

    SettingsStorageModule getSettingsStorageModule() {
        if (this.settingsStorageModule == null) {
            this.settingsStorageModule = SettingsStorageModule.getInstance();
        }
        return this.settingsStorageModule;
    }

    StatusEventManager getStatusEventManager() {
        if (this.statusEventManager == null) {
            this.statusEventManager = StatusEventManager.getInstance();
        }
        return this.statusEventManager;
    }

    public synchronized void onAccessoryConnectionChanged(String str, boolean z) {
        if (z) {
            onAccessoryConnected(str);
        } else {
            onAccessoryDisconnected(str);
        }
    }

    public void refreshConnectedAccessory() {
        Log.i(TAG, "Refreshes connected accessory");
        for (Accessory accessory : AccessoryProvider.getConnectedAccessoryList()) {
            onAccessoryConnectionChanged(AccessoryProvider.getAccessoryDeviceType(accessory), true);
        }
    }

    public synchronized void setLowDistractionMode(String str, boolean z) {
        DistractionMode distractionMode = this.distractionModeMap.get(str);
        if (distractionMode != null) {
            distractionMode.setLowDistractionMode(z);
        } else {
            Log.w(TAG, "setLowDistractionMode - TTarget deviceType is not connected");
        }
    }

    public synchronized void setNoDistractionMode(String str, boolean z) {
        DistractionMode distractionMode = this.distractionModeMap.get(str);
        if (distractionMode != null) {
            distractionMode.setNoDistractionMode(z);
        } else {
            Log.i(TAG, "setNoDistractionMode - Target deviceType is not connected");
        }
    }

    public synchronized void setSilentDistractionMode(String str, boolean z) {
        DistractionMode distractionMode = this.distractionModeMap.get(str);
        if (distractionMode != null) {
            distractionMode.setSilentDistractionMode(z);
            getSettingsStorageModule().putSilentDistractionModeValue(str, z);
            getStatusEventManager().onSilentDistractionModeChanged(getCurrentDistractionMode() == 3);
        } else {
            Log.w(TAG, "setSilentDistractionMode - Target deviceType is not connected");
        }
    }

    @VisibleForTesting
    MultiDeviceDistractionModeProvider(StatusEventManager statusEventManager, SettingsStorageModule settingsStorageModule) {
        this();
        this.statusEventManager = statusEventManager;
        this.settingsStorageModule = settingsStorageModule;
    }
}
