package com.amazon.alexa.handsfree.notification.metrics;
/* loaded from: classes8.dex */
public enum MetricType {
    HANDSFREE_NOT_ACTIVE("HandsFreeNotActive:%s"),
    NOTIFICATION_NOT_ACTIVE("NotificationNotActive:%s"),
    NOTIFICATION_CONTRACT_NULL_TIME_BASED("NotificationContractNullTimeBased:%s"),
    NOTIFICATION_CONTRACT_NULL_UTTERANCE("NotificationContractNullUtterance:%s"),
    NOTIFICATION_CONTRACT_NULL_QUICK_SETTINGS("NotificationContractNullQuickSettings:%s"),
    NOTIFICATION_MODULE_NOT_INITIALIZED_TIME_BASED_RECEIVER("NotificationModuleNotInitializedTimeBasedReceiver:%s"),
    NOTIFICATION_MODULE_NOT_INITIALIZED_UTTERANCE_RECEIVER("NotificationModuleNotInitializedUtteranceReceiver:%s"),
    NOTIFICATION_MODULE_NOT_INITIALIZED_KILL_SWITCH_RECEIVER("NotificationModuleNotInitializedKillSwitchReceiver:%s"),
    NOTIFICATION_MODULE_NOT_INITIALIZED_TERMS_CHECKER_RECEIVER("NotificationModuleNotInitializedTermsCheckerReceiver:%s"),
    NOTIFICATION_MODULE_NOT_INITIALIZED_QUICK_SETTINGS_RECEIVER("NotificationModuleNotInitializedQuickSettingsReceiver:%s"),
    NOTIFICATION_MODULE_NOT_INITIALIZED_STATE_CHANGE_RECEIVER("NotificationModuleNotInitializedStateChangeReceiver:%s"),
    NOTIFICATION_MODULE_NOT_INITIALIZED_PERMISSION_RECEIVER("NotificationModuleNotInitializedPermissionReceiver:%s"),
    NOTIFICATION_MODULE_NOT_INITIALIZED_LANGUAGE_SWITCHING_RECEIVER("NotificationModuleNotInitializedLanguageSwitchingReceiver:%s");
    
    private String mValue;

    MetricType(String str) {
        this.mValue = str;
    }

    public String getValue() {
        return this.mValue;
    }
}
