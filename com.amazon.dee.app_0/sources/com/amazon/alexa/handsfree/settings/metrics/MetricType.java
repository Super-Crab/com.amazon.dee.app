package com.amazon.alexa.handsfree.settings.metrics;
/* loaded from: classes8.dex */
public enum MetricType {
    DECIDER_NOT_ACTIVE("DeciderNotActive:%s"),
    SETTINGS_NOT_ACTIVE("SettingsNotActive:%s"),
    HANDSFREE_NOT_ACTIVE("HandsFreeNotActive:%s"),
    HANDSFREE_MANAGER_SYNC_FAILED("HandsFreeManagerSyncFailed"),
    HANDSFREE_MANAGER_SYNC_SUCCESS("HandsFreeManagerSyncSuccess"),
    HANDSFREE_MANAGER_QUICK_SETTINGS_TILE_UPDATE_SUCCESS("HandsFreeManagerQuickSettingsTileSuccess"),
    HANDSFREE_MANAGER_TOGGLE_SUCCESS("HandsFreeManagerToggleSuccess"),
    WAKEWORD_DETECTION_TOGGLE_SUCCESS("WakeWordDetectionTurnOffSuccess"),
    SETTINGS_SETUP_CONTRACT_NOT_AVAILABLE_METRIC("SettingSetupFlowContractNotAvailable"),
    PREFERENCES_PRESENTER_NOT_AVAILABLE_FOR_CLEANUP("PreferencesPresenterNotAvailableForCleanup"),
    DELETE_LOCAL_VOICE_PROFILE_FROM_VOX_SUCCESS("DeleteLocalVoiceProfileFromVoxSuccess:%s"),
    HANDS_FREE_ENABLED("HandsFreeEnabled:%s"),
    HANDS_FREE_ENABLED_WITH_CALLER("HandsFreeEnabled:%s:%s"),
    HANDS_FREE_NOT_ENABLED("HandsFreeNotEnabled:%s"),
    HANDS_FREE_NOT_ENABLED_WITH_CALLER("HandsFreeNotEnabled:%s:%s");
    
    private String mValue;

    MetricType(String str) {
        this.mValue = str;
    }

    public String getValue() {
        return this.mValue;
    }
}
