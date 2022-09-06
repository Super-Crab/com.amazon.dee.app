package com.amazon.alexa.handsfree.uservoicerecognition.model;

import androidx.annotation.NonNull;
/* loaded from: classes8.dex */
public enum UVRComponent {
    FIRST_STAGE("FirstStage"),
    SECOND_STAGE("SecondStage"),
    ANTI_SPOOF("AntiSpoof"),
    VOICE_TRAINING("VoiceTraining");
    
    private final String mMetricName;

    UVRComponent(@NonNull String str) {
        this.mMetricName = str;
    }

    public String getMetricName() {
        return this.mMetricName;
    }

    @Override // java.lang.Enum
    public String toString() {
        return name();
    }
}
