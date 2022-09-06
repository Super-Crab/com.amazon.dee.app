package com.amazon.alexa.handsfree.notification;

import androidx.annotation.ArrayRes;
import androidx.annotation.NonNull;
import com.amazon.alexa.handsfree.notification.metrics.NotificationEventMetadata;
import com.amazon.alexa.handsfree.settings.voxsettings.VoxSettingsRequestHandler;
/* loaded from: classes8.dex */
public enum NotificationType {
    TIME_BASED("TIME_BASED_COUNT", "timeBased", NotificationEventMetadata.Component.HANDSFREE_SETUP, NotificationEventMetadata.NotificationType.TIME_BASED, false, R.array.decider_notification_time_intervals),
    UTTERANCE("UTTERANCE_BASED_COUNT", "utteranceBased", NotificationEventMetadata.Component.HANDSFREE_SETUP, NotificationEventMetadata.NotificationType.UTTERANCE_BASED, true),
    KILL_SWITCH("KILL_SWITCH_COUNT", "killSwitch", NotificationEventMetadata.Component.HANDSFREE_SETUP, NotificationEventMetadata.NotificationType.DISABLED_STATE, false, R.array.decider_notification_time_intervals),
    VOICE_PROFILE("VOICE_PROFILE_COUNT", "voiceProfile", NotificationEventMetadata.Component.UVR_SETUP, NotificationEventMetadata.NotificationType.VOICE_PROFILE, true),
    VOICE_PROFILE_ON_LOCK_SCREEN("VOICE_PROFILE_ON_LOCK_SCREEN_COUNT", "voiceProfileOnLockScreen", NotificationEventMetadata.Component.UVR_SETUP, NotificationEventMetadata.NotificationType.VOICE_PROFILE_ON_LOCK_SCREEN, true),
    SHOW_ON_LOCK_SCREEN("SHOW_ON_LOCK_SCREEN_COUNT", VoxSettingsRequestHandler.SHOW_ON_LOCKSCREEN_PREF_KEY, NotificationEventMetadata.Component.UVR_SETUP, NotificationEventMetadata.NotificationType.SHOW_ON_LOCK_SCREEN, true),
    QUICK_SETTINGS("QUICK_SETTINGS_COUNT", "quickSettings", NotificationEventMetadata.Component.HANDSFREE_SETUP, NotificationEventMetadata.NotificationType.QUICK_SETTINGS, false),
    PERMISSION_REQUEST("PERMISSION_REQUEST", "permissionRequest", NotificationEventMetadata.Component.HANDSFREE_SETUP, NotificationEventMetadata.NotificationType.PERMISSION_REQUEST, false, R.array.permission_notification_time_intervals),
    ENABLE_HANDS_FREE("ENABLE_HANDS_FREE_COUNT", "enableHandsFree", NotificationEventMetadata.Component.HANDSFREE_SETUP, NotificationEventMetadata.NotificationType.ENABLE_HANDS_FREE, false, R.array.enable_hands_free_notification_time_intervals),
    LANGUAGE_SWITCHING("LANGUAGE_SWITCHING", "languageSwitching", NotificationEventMetadata.Component.HANDSFREE_SETUP, NotificationEventMetadata.NotificationType.LANGUAGE_SWITCHING, false);
    
    private final NotificationEventMetadata.Component mComponent;
    @ArrayRes
    private final int mDefaultTimeIntervalsResId;
    private final boolean mIsTriggeredByUtterance;
    private final NotificationEventMetadata.NotificationType mNotificationType;
    private final String mRemoteConfigKey;
    private final String mSharedPreferencesKey;

    NotificationType(@NonNull String str, @NonNull String str2, @NonNull NotificationEventMetadata.Component component, @NonNull NotificationEventMetadata.NotificationType notificationType, boolean z) {
        this(str, str2, component, notificationType, z, 0);
    }

    public NotificationEventMetadata.Component getComponent() {
        return this.mComponent;
    }

    @ArrayRes
    public int getDefaultTimeIntervalsResId() {
        return this.mDefaultTimeIntervalsResId;
    }

    public NotificationEventMetadata.NotificationType getNotificationType() {
        return this.mNotificationType;
    }

    public String getRemoteConfigKey() {
        return this.mRemoteConfigKey;
    }

    public String getSharedPreferencesKey() {
        return this.mSharedPreferencesKey;
    }

    public boolean isTriggeredByUtterance() {
        return this.mIsTriggeredByUtterance;
    }

    @Override // java.lang.Enum
    public String toString() {
        return name();
    }

    NotificationType(@NonNull String str, @NonNull String str2, @NonNull NotificationEventMetadata.Component component, @NonNull NotificationEventMetadata.NotificationType notificationType, boolean z, @ArrayRes int i) {
        this.mSharedPreferencesKey = str;
        this.mRemoteConfigKey = str2;
        this.mComponent = component;
        this.mNotificationType = notificationType;
        this.mIsTriggeredByUtterance = z;
        this.mDefaultTimeIntervalsResId = i;
    }
}
