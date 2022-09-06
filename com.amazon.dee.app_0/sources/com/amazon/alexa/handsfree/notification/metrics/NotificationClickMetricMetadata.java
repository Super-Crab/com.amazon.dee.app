package com.amazon.alexa.handsfree.notification.metrics;
/* loaded from: classes8.dex */
public interface NotificationClickMetricMetadata {

    /* loaded from: classes8.dex */
    public enum Component {
        NOTIFICATION
    }

    /* loaded from: classes8.dex */
    public enum PageType {
        TIME_BASED_NOTIFICATION,
        UTTERANCE_BASED_NOTIFICATION,
        DISABLED_STATE_NOTIFICATION,
        VOICE_PROFILE_NOTIFICATION,
        VOICE_PROFILE_ON_LOCK_SCREEN_NOTIFICATION,
        SHOW_ON_LOCK_SCREEN_NOTIFICATION,
        QUICK_SETTINGS_NOTIFICATION,
        PARTNER_VOICE_APP_DOWNLOAD_NOTIFICATION,
        ENABLE_HANDS_FREE_NOTIFICATION,
        LANGUAGE_SWITCHING_NOTIFICATION
    }

    /* loaded from: classes8.dex */
    public enum SubPageType {
        NONE,
        QS_TILE_IN_EDIT_MENU,
        QS_TILE_IN_MAIN_MENU,
        ENABLE_HANDS_FREE_CONFIRM_BUTTON,
        ENABLE_HANDS_FREE_LATER_BUTTON
    }
}
