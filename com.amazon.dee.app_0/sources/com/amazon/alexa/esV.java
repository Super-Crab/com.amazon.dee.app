package com.amazon.alexa;

import com.amazon.alexa.api.LaunchType;
import com.amazon.alexa.client.metrics.core.AlexaMetricsConstants;
/* compiled from: LaunchSource.java */
/* loaded from: classes.dex */
public enum esV {
    INTERNAL("internal"),
    REQUEST_DIALOG("requestDialog"),
    NOTIFICATION_TAP("notificationTap"),
    WAKE_WORD(AlexaMetricsConstants.EventConstants.WAKE_WORD),
    EXPECT_SPEECH("expectSpeech"),
    TEXT("text"),
    UNKNOWN("unknown");
    
    public final String value;

    esV(String str) {
        this.value = str;
    }

    public String zZm() {
        return this.value;
    }

    public static esV zZm(LaunchType launchType) {
        int i = PqU.zZm[launchType.ordinal()];
        if (i != 1) {
            if (i == 2) {
                return REQUEST_DIALOG;
            }
            if (i != 3) {
                return UNKNOWN;
            }
            return TEXT;
        }
        return WAKE_WORD;
    }
}
