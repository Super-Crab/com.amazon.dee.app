package com.amazon.alexa.handsfree.notification;

import android.content.ComponentName;
import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.handsfree.devices.DeviceInformation;
import com.amazon.alexa.handsfree.devices.DeviceTypeInformationProvider;
import com.amazon.alexa.handsfree.notification.api.NotificationContract;
import com.amazon.alexa.handsfree.notification.channels.NotificationChannelManager;
import com.amazon.alexa.handsfree.notification.configurations.languageswitching.LanguageSwitchingConfigHandler;
import com.amazon.alexa.handsfree.notification.configurations.quicksettings.QuickSettingsConfigHandler;
import com.amazon.alexa.handsfree.notification.configurations.scheduler.NotificationScheduler;
import com.amazon.alexa.handsfree.notification.configurations.timebased.TimeIntervalConfigHandler;
import com.amazon.alexa.handsfree.notification.notifiers.EnableHandsFreeNotifier;
import com.amazon.alexa.handsfree.notification.receivers.UtteranceReceiver;
import com.amazon.alexa.handsfree.protocols.features.HandsFreeComponent;
import com.amazon.alexa.handsfree.protocols.features.HandsFreeUserIdentity;
import com.amazon.alexa.handsfree.protocols.settings.WakeWordSettingsManager;
import com.amazon.alexa.handsfree.protocols.settings.WakeWordSettingsManagerProvider;
import com.amazon.alexa.handsfree.protocols.utils.Log;
/* loaded from: classes8.dex */
public class NotificationModule {
    @VisibleForTesting
    static final HandsFreeComponent NOTIFICATION_COMPONENT = HandsFreeComponent.HANDS_FREE_NOTIFICATIONS;
    private static final String TAG = NotificationModule.class.getSimpleName();
    private DeviceTypeInformationProvider mDeviceTypeInformationProvider;
    private HandsFreeUserIdentity mHandsFreeUserIdentity;
    private boolean mIsInitialized;
    private boolean mIsWeblabNotificationScheduled;
    private NotificationContract mNotificationContract;
    private EnableHandsFreeNotifier.PreferenceManager mPreferenceManager;
    private WakeWordSettingsManager mWakeWordSettingsManager;

    /* loaded from: classes8.dex */
    private static class LazyHolder {
        private static final NotificationModule INSTANCE = new NotificationModule();

        private LazyHolder() {
        }
    }

    public static NotificationModule getInstance() {
        return LazyHolder.INSTANCE;
    }

    private void scheduleNotifications(@NonNull Context context) {
        getTimeBasedNotificationScheduler(context).scheduleNextNotification();
        getQuickSettingsNotificationScheduler(context).scheduleNextNotification();
        getLanguageSwitchingNotificationScheduler(context).scheduleNextNotification();
        if (this.mPreferenceManager.shouldShowEnableHandsFreeNotification(context)) {
            getEnableHandsFreeNotificationScheduler(context).scheduleNextNotification();
        }
    }

    private void scheduleWeblabNotifications(boolean z, @NonNull Context context) {
        DeviceInformation supportedDeviceInformation = this.mDeviceTypeInformationProvider.getSupportedDeviceInformation(context);
        if (supportedDeviceInformation == null || supportedDeviceInformation.isDeviceLaunched()) {
            return;
        }
        boolean hasComponent = this.mHandsFreeUserIdentity.hasComponent(NOTIFICATION_COMPONENT);
        if (!z || !hasComponent || this.mIsWeblabNotificationScheduled) {
            return;
        }
        Log.i(TAG, "scheduleWeblabNotifications.");
        scheduleNotifications(context);
        this.mIsWeblabNotificationScheduled = true;
    }

    @VisibleForTesting
    void enableUtteranceNotification(@NonNull Context context) {
        context.getPackageManager().setComponentEnabledSetting(new ComponentName(context, UtteranceReceiver.class), 1, 1);
    }

    @Nullable
    public NotificationContract getContract() {
        return this.mNotificationContract;
    }

    @NonNull
    @VisibleForTesting
    NotificationScheduler getEnableHandsFreeNotificationScheduler(@NonNull Context context) {
        return new NotificationScheduler(NotificationType.ENABLE_HANDS_FREE, context, new TimeIntervalConfigHandler(context));
    }

    @Nullable
    public HandsFreeUserIdentity getHandsFreeUserIdentity() {
        return this.mHandsFreeUserIdentity;
    }

    @NonNull
    @VisibleForTesting
    NotificationScheduler getLanguageSwitchingNotificationScheduler(@NonNull Context context) {
        return new NotificationScheduler(NotificationType.LANGUAGE_SWITCHING, context, new LanguageSwitchingConfigHandler(context));
    }

    @NonNull
    @VisibleForTesting
    NotificationScheduler getPermissionNotificationScheduler(@NonNull Context context) {
        return new NotificationScheduler(NotificationType.PERMISSION_REQUEST, context, new TimeIntervalConfigHandler(context));
    }

    @NonNull
    @VisibleForTesting
    NotificationScheduler getQuickSettingsNotificationScheduler(@NonNull Context context) {
        return new NotificationScheduler(NotificationType.QUICK_SETTINGS, context, new QuickSettingsConfigHandler(context));
    }

    @NonNull
    @VisibleForTesting
    NotificationScheduler getTimeBasedNotificationScheduler(@NonNull Context context) {
        return new NotificationScheduler(NotificationType.TIME_BASED, context, new TimeIntervalConfigHandler(context));
    }

    public synchronized void initialize(@NonNull NotificationContract notificationContract, @NonNull Context context) {
        if (!this.mIsInitialized) {
            this.mNotificationContract = notificationContract;
            this.mDeviceTypeInformationProvider = DeviceTypeInformationProvider.getInstance(context);
            this.mWakeWordSettingsManager = WakeWordSettingsManagerProvider.getInstance().get();
            this.mPreferenceManager = new EnableHandsFreeNotifier.PreferenceManager();
            scheduleInitializationNotifications(context);
            new NotificationChannelManager(context).createNotificationChannels();
            enableUtteranceNotification(context);
            this.mIsInitialized = true;
        }
    }

    public boolean isInitialized() {
        return this.mIsInitialized;
    }

    public boolean isWeblabNotificationScheduled() {
        return this.mIsWeblabNotificationScheduled;
    }

    @VisibleForTesting
    void scheduleInitializationNotifications(@NonNull Context context) {
        DeviceInformation supportedDeviceInformation = this.mDeviceTypeInformationProvider.getSupportedDeviceInformation(context);
        if (supportedDeviceInformation == null || !supportedDeviceInformation.isDeviceLaunched()) {
            return;
        }
        Log.i(TAG, "scheduleInitializationNotifications.");
        scheduleNotifications(context);
    }

    public void schedulePermissionNotification(@NonNull Context context) {
        getPermissionNotificationScheduler(context).scheduleNextNotification();
    }

    public void setHandsFreeUserIdentity(boolean z, @NonNull Context context, @NonNull HandsFreeUserIdentity handsFreeUserIdentity) {
        this.mHandsFreeUserIdentity = handsFreeUserIdentity;
        if (!isWeblabNotificationScheduled()) {
            scheduleWeblabNotifications(z, context);
        }
    }

    private NotificationModule() {
    }

    @VisibleForTesting
    NotificationModule(@NonNull NotificationContract notificationContract, @NonNull HandsFreeUserIdentity handsFreeUserIdentity, @NonNull DeviceTypeInformationProvider deviceTypeInformationProvider, @NonNull WakeWordSettingsManager wakeWordSettingsManager, @NonNull EnableHandsFreeNotifier.PreferenceManager preferenceManager) {
        this.mNotificationContract = notificationContract;
        this.mHandsFreeUserIdentity = handsFreeUserIdentity;
        this.mDeviceTypeInformationProvider = deviceTypeInformationProvider;
        this.mWakeWordSettingsManager = wakeWordSettingsManager;
        this.mPreferenceManager = preferenceManager;
    }
}
