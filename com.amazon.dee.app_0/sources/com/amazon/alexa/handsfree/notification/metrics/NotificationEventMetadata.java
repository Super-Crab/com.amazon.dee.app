package com.amazon.alexa.handsfree.notification.metrics;
/* loaded from: classes8.dex */
public interface NotificationEventMetadata {

    /* loaded from: classes8.dex */
    public enum Component {
        HANDSFREE_SETUP,
        UVR_SETUP,
        DLS_SETUP
    }

    /* loaded from: classes8.dex */
    public enum ConnectionFailureReason {
        ALEXA_LEADER_IS_MISSED,
        AUTHORIZATION_IS_MISSED,
        UNKNOWN_REASON
    }

    /* loaded from: classes8.dex */
    public enum NotificationType {
        TIME_BASED,
        UTTERANCE_BASED,
        DISABLED_STATE,
        VOICE_PROFILE,
        VOICE_PROFILE_ON_LOCK_SCREEN,
        SHOW_ON_LOCK_SCREEN,
        QUICK_SETTINGS,
        PERMISSION_REQUEST,
        ENABLE_HANDS_FREE,
        LANGUAGE_SWITCHING
    }
}
