package com.amazon.alexa.photos.hva;
/* loaded from: classes9.dex */
public enum HVAEvent {
    STARTED_OUT_BOUND("highValueActivity::started"),
    ENDED_OUT_BOUND("highValueActivity::ended");
    
    private final String value;

    HVAEvent(String str) {
        this.value = str;
    }

    public String getValue() {
        return this.value;
    }
}
