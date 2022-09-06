package com.amazon.ptz.digital;
/* loaded from: classes13.dex */
public enum DigitalMagnitudes {
    FULL_ZOOM_OUT(1.0f),
    TOGGLE_MAX_ZOOM(1.45f),
    DIGITAL_MAX_ZOOM(5.0f),
    PHYSICAL_PAN_STEP(250.0f),
    DIGITAL_PAN_STEP(50.0f),
    DIGITAL_ZOOM_IN(1.45f),
    DIGITAL_ZOOM_OUT(0.5f);
    
    private final float mValue;

    DigitalMagnitudes(float f) {
        this.mValue = f;
    }

    public float getMagnitude() {
        return this.mValue;
    }
}
