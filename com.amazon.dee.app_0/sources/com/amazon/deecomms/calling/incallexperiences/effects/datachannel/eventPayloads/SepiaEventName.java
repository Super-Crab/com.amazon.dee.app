package com.amazon.deecomms.calling.incallexperiences.effects.datachannel.eventPayloads;

import androidx.annotation.NonNull;
import com.google.common.base.Enums;
/* loaded from: classes12.dex */
public enum SepiaEventName {
    APPLY_EFFECT("apply_effect"),
    REMOVE_EFFECT("remove_effect"),
    ACK_STATUS("ack_status"),
    CURRENT_STATUS("current_status"),
    BEGIN_EFFECTS_SESSION("begin_effects_session"),
    SCREEN_TOUCH("screen_touch"),
    SEPIA_METRICS("sepia_metrics");
    
    private String value;

    SepiaEventName(String str) {
        this.value = str;
    }

    public static SepiaEventName lookupByEventName(String str) {
        return (SepiaEventName) Enums.getIfPresent(SepiaEventName.class, str.toUpperCase()).orNull();
    }

    @Override // java.lang.Enum
    @NonNull
    public String toString() {
        return this.value;
    }
}
