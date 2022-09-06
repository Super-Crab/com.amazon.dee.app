package com.amazon.alexa.handsfree.settings.metrics;
/* loaded from: classes8.dex */
public interface ClickMetricMetadata {

    /* loaded from: classes8.dex */
    public enum Action {
        ENABLE,
        DISABLE,
        DELETE,
        DELETE_CONFIRM,
        DELETE_CANCEL,
        TILE_ADDED,
        TILE_REMOVED,
        START_VOICE_TRAINING,
        BLOCK_ALL,
        BLOCK_PERSONAL,
        BLOCK_NONE,
        ALERT_DIALOG_DENY
    }

    /* loaded from: classes8.dex */
    public enum Component {
        SETTINGS_TILE,
        SETTINGS_MENU
    }

    /* loaded from: classes8.dex */
    public enum PageType {
        RESPOND_ON_LOCK_SCREEN,
        ANDROID_SETTINGS,
        QUICK_SETTINGS,
        VOICE_PROFILE
    }
}
