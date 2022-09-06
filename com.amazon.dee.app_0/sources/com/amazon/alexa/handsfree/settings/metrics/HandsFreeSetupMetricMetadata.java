package com.amazon.alexa.handsfree.settings.metrics;
/* loaded from: classes8.dex */
public interface HandsFreeSetupMetricMetadata {

    /* loaded from: classes8.dex */
    public enum ActionType {
        INTENTION,
        DISCOVERY
    }

    /* loaded from: classes8.dex */
    public enum Component {
        SETTINGS_TILE,
        HANDSFREE_SETUP,
        SETTINGS_MENU,
        NOTIFICATION
    }

    /* loaded from: classes8.dex */
    public enum EventType {
        CLICK,
        ALLOW,
        DENY,
        DENY_NEVER_ASK_AGAIN,
        EXIT,
        SETUP_COMPLETE,
        NONE
    }

    /* loaded from: classes8.dex */
    public enum PageType {
        QUICK_SETTINGS,
        DISABLED_STATE_NOTIFICATION,
        ANDROID_SETTINGS,
        RECORD_AUDIO_PERMISSIONS,
        ALEXA_APP_UPDATE,
        ALEXA_APP,
        SETUP_NOTIFICATION,
        PROFILE_SETTINGS,
        NONE
    }

    /* loaded from: classes8.dex */
    public enum SubPageType {
        NONE,
        ALLOW_BUTTON,
        LATER_BUTTON,
        CREATE_BUTTON
    }
}
