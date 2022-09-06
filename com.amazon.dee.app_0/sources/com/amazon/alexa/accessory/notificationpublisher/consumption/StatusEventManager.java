package com.amazon.alexa.accessory.notificationpublisher.consumption;

import com.amazon.alexa.accessory.notificationpublisher.ProcessNotificationModule;
import com.amazon.alexa.accessory.notificationpublisher.urldownloader.DownloadResponseHandler;
import com.amazon.alexa.accessory.notificationpublisher.utils.DownloadableAudioFileHelper;
import com.amazon.alexa.accessory.notificationpublisher.utils.Log;
import com.amazon.alexa.accessory.notificationpublisher.utils.NotificationConstants;
import com.amazon.alexa.accessory.notificationpublisher.utils.NotificationFileHelper;
/* loaded from: classes.dex */
public final class StatusEventManager extends BaseComponent {
    public static final int EVENT_ACCESSORY_DND_OFF = 9;
    public static final int EVENT_ACCESSORY_DND_ON = 8;
    public static final int EVENT_DEVICE_CONNECTED = 4;
    public static final int EVENT_DEVICE_DISCONNECTED = 3;
    public static final int EVENT_FEATURE_TOGGLE_OFF = 1;
    public static final int EVENT_FEATURE_TOGGLE_ON = 2;
    public static final int EVENT_LDM_OFF = 11;
    public static final int EVENT_LDM_ON = 10;
    public static final int EVENT_NO_A2DP_CONNECTIONS_ACTIVE = 16;
    public static final int EVENT_PAUSE_NEW_REQUEST_DISABLED = 13;
    public static final int EVENT_PAUSE_NEW_REQUEST_ENABLED = 12;
    public static final int EVENT_PHONE_DND_OFF = 7;
    public static final int EVENT_PHONE_DND_ON = 6;
    public static final int EVENT_SCHEDULE_AUDIO_FILE_CLEAN_UP = 17;
    public static final int EVENT_SDM_OFF = 19;
    public static final int EVENT_SDM_ON = 18;
    public static final int EVENT_SETTINGS_CLEARED = 5;
    public static final int EVENT_VIP_FILTER_DISABLED = 15;
    public static final int EVENT_VIP_FILTER_ENABLED = 14;
    private static final String TAG = "StatusEventManager";
    private static StatusEventManager instance;

    private StatusEventManager() {
        super(6);
    }

    public static synchronized StatusEventManager getInstance() {
        StatusEventManager statusEventManager;
        synchronized (StatusEventManager.class) {
            if (instance == null) {
                instance = new StatusEventManager();
            }
            statusEventManager = instance;
        }
        return statusEventManager;
    }

    public static synchronized void releaseInstance() {
        synchronized (StatusEventManager.class) {
            instance = null;
        }
    }

    public void onClearFilterSettings() {
        Log.i(TAG, "onClearFilterSettings");
        postEventMessage(5);
        DownloadableAudioFileHelper.clearAudioFolder(NotificationConstants.DEFAULT_LOCALE.toString(), DownloadResponseHandler.TYPE_TO_NAME_MAP.mo7740get(3));
    }

    public void onDeviceConnectionChanged(boolean z) {
        String str = TAG;
        Log.i(str, "onDeviceConnectionChanged - connected: " + z);
        if (!z) {
            NotificationEventManager.getInstance().clearIncomingNotifications();
            postEventMessage(3);
            return;
        }
        postEventMessage(4);
    }

    public void onFeatureToggleChanged(boolean z) {
        String str = TAG;
        Log.d(str, "onFeatureToggleChanged - flag: " + z);
        if (!z) {
            NotificationEventManager.getInstance().clearIncomingNotifications();
            ProcessNotificationModule.getProcessNotificationModule().removePendingNotificationProcessRequest();
            postEventMessage(1);
            return;
        }
        postEventMessage(2);
    }

    public void onLowDistractionModeChanged(boolean z) {
        String str = TAG;
        Log.i(str, "onLowDistractionModeChanged - ldmEnabled: " + z);
        postEventMessage(z ? 10 : 11);
    }

    public void onNoA2dpConnectionsActive() {
        Log.i(TAG, "onNoA2dpConnectionsActive");
        postEventMessage(16);
    }

    public synchronized void onPauseNewRequestToggleChanged(boolean z) {
        String str = TAG;
        Log.i(str, "onPauseNewRequestToggleChanged -- enabled: " + z);
        if (z) {
            postEventMessage(12);
        } else {
            postEventMessage(13);
        }
    }

    public void onPerformAudioFileCleanUp() {
        Log.i(TAG, "onPerformAudioFileCleanUp");
        postEventMessageDelayed(17, DownloadableAudioFileHelper.DEFAULT_CLEANUP_TASK_DELAY);
        DownloadableAudioFileHelper.deleteExpiredFiles(NotificationConstants.DEFAULT_LOCALE.toString(), DownloadResponseHandler.TYPE_TO_NAME_MAP.mo7740get(3), DownloadableAudioFileHelper.DEFAULT_RETENTION_PERIOD);
        NotificationFileHelper.deleteExpiredFiles(DownloadableAudioFileHelper.DEFAULT_RETENTION_PERIOD);
    }

    public void onPhoneDNDChanged(boolean z) {
        String str = TAG;
        Log.i(str, "onPhoneDNDChanged - isDndOn: " + z);
        if (z) {
            NotificationEventManager.getInstance().clearIncomingNotifications();
            postEventMessage(6);
            return;
        }
        postEventMessage(7);
    }

    public void onSilentDistractionModeChanged(boolean z) {
        String str = TAG;
        Log.i(str, "onSilentDistractionModeChanged - sdmEnabled: " + z);
        postEventMessage(z ? 18 : 19);
    }

    public synchronized void onVipFilterToggleChanged(boolean z) {
        String str = TAG;
        Log.i(str, "onVipFilterToggleChanged -- enabled: " + z);
        if (z) {
            postEventMessage(14);
        } else {
            postEventMessage(15);
        }
    }

    /* renamed from: clone */
    public StatusEventManager m350clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException("Cannot clone a singleton");
    }
}
